import { Component, OnInit, ViewChild } from "@angular/core"
import { Form, FormControl, FormGroup, FormsModule, NgForm, ReactiveFormsModule, Validators } from "@angular/forms"
import {Utilisateur} from "../../models/utilisateur.model";
import { UtilisateurRequest } from "../../models/utils/utilisateurRequest.model"
import { UtilisateurService } from "../../services/utilisateur.service"
import { NotificationService } from "../../services/notification.service"
import { DatePipe, NgClass, NgForOf, NgIf } from "@angular/common"
import { FormationService } from "../../services/formation.service"
import { ExperienceService } from "../../services/experience.service"
import { LoisirService } from "../../services/loisir.service"
import { SocialMediaService } from "../../services/social-media.service"
import { CompetenceService } from "../../services/competence.service"
import { Formation } from "../../models/formation.model"
import { Competence } from "../../models/competence.model"
import { Experience } from "../../models/experience.model"
import { Loisir } from "../../models/loisir.model"
import { SocialMedia } from "../../models/social-media.model"
import { MatTab, MatTabGroup } from "@angular/material/tabs"
import { MatTooltip } from "@angular/material/tooltip"
import { MatIcon } from "@angular/material/icon"

@Component({
  selector: 'profile',
  standalone: true,
  imports: [
    FormsModule,
    ReactiveFormsModule,
    NgIf,
    NgForOf,
    NgClass,
    DatePipe,
    MatTab,
    MatTabGroup,
    MatTooltip,
    MatIcon,
  ],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.scss'
})
export class UserComponent implements OnInit{
  currentUser! : Utilisateur | any;
  currentFormation! : any;
  currentExperience! : any;
  currentCompetence! : any;
  currentLoisir! : any;
  currentSocialMedias! : any;
  userInfoForm : FormGroup;
  competenceForm : FormGroup;
  experienceForm : FormGroup;
  loisirForm : FormGroup;
  socialMediaForm : FormGroup;
  formationForm : FormGroup;
  public refreshing! : boolean;
  selectedFormation!: Formation
  selectedCompetence!: Competence;
  selectedExperience!: Experience
  selectedLoisir!: Loisir;
  selectedSocialMedia!: SocialMedia;
  hideShowButtonUpdateDeleteLoisirForm!: boolean;
  hideShowButtonUpdateDeleteSocialMediaForm!: boolean;

  socialMediaOptions: string[] = [
    "Facebook",
    "WhatsApp",
    "Instagram",
    "Facebook Messenger",
    "Snapchat",
    "TikTok",
    "Twitter",
    "Pinterest",
    "iMessage",
    "LinkedIn",
    "Autre"
  ];

  constructor(private utlisateurService: UtilisateurService,
              private formationService: FormationService,
              private experienceService: ExperienceService,
              private competenceService: CompetenceService,
              private loisirService: LoisirService,
              private socialMediaService: SocialMediaService,
              private notificationService: NotificationService) {

    this.userInfoForm = new FormGroup({
      id: new FormControl(''),
      username: new FormControl('', Validators.required),
      name: new FormControl('', Validators.required),
      email: new FormControl('', Validators.required),
      password : new FormControl('', Validators.required),
      pays: new FormControl('', Validators.required),
      profilePicture: new FormControl(''),
      telephone: new FormControl('', Validators.required),
      ville: new FormControl('', Validators.required)
    });

    this.formationForm = new FormGroup({
      id: new FormControl(''),
      diplome: new FormControl('', Validators.required),
      domaineEtudes: new FormControl('', Validators.required),
      ecole: new FormControl('', Validators.required),
      lieu: new FormControl('', Validators.required),
      anneeDebut: new FormControl('', Validators.required),
      anneeFin: new FormControl('', Validators.required)
    });

    this.competenceForm = new FormGroup({
      id: new FormControl(''),
      competence: new FormControl('', Validators.required),
      niveau: new FormControl('', Validators.required)
    });

    this.experienceForm = new FormGroup({
      id: new FormControl(''),
      titre: new FormControl('', Validators.required),
      entreprise: new FormControl('', Validators.required),
      lieu: new FormControl('', Validators.required),
      description: new FormControl('', Validators.required),
      dateDebut: new FormControl('', Validators.required),
      dateFin: new FormControl('', Validators.required)
    });

    this.loisirForm = new FormGroup({
      id: new FormControl(''),
      hobby: new FormControl('', Validators.required)
    });

    this.socialMediaForm = new FormGroup({
      id: new FormControl(''),
      type: new FormControl('', Validators.required),
      lien: new FormControl('', Validators.required)
    });
  }

  ngOnInit(): void {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser') || '{}');
    this.userInfoForm.patchValue(this.currentUser);
    this.initCurrentExpFormLoiSocial();
  }

  initCurrentExpFormLoiSocial() : void {
    if(this.currentUser){
      this.currentFormation =  this.currentUser.formations;
      this.currentCompetence = this.currentUser.competences;
      this.currentExperience = this.currentUser.experiences;
      this.currentLoisir = this.currentUser.loisirs;
      this.currentSocialMedias = this.currentUser.socialMedias;
    }
  }

  updateUserInfo() : void {
    console.log(this.userInfoForm?.value);
    this.refreshing = true;
    this.utlisateurService.updateUtilisateur(this.userInfoForm?.value).subscribe({
      next: (response: Utilisateur) => {
        // remplacer les champs email, telephone, ville et pays, username, name de l'objet currentUser par ceux de l'objet response
        this.currentUser.email = response.email;
        this.currentUser.telephone = response.telephone;
        this.currentUser.ville = response.ville;
        this.currentUser.pays = response.pays;
        this.currentUser.username = response.username;
        this.currentUser.name = response.name;
        localStorage.setItem('currentUser', JSON.stringify(this.currentUser));

        this.notificationService.onSuccess("User info updated successfully");
        this.refreshing = false;
      },
      error: (error: any) => {
        console.log(error);
        this.notificationService.onError("An error occurred while updating user info");
        this.refreshing = false;
      },
      complete: () => {
        console.log('Complete');
      }
    })
  }

  ajouterFormation() : void {
    this.formationService.saveFormation(this.currentUser.id, this.formationForm?.value).subscribe({
      next: (response: Formation) => {
        this.notificationService.onSuccess("Formation info added successfully");
        this.formationForm.reset();
        this.getUserFormations();
        this.refreshing = false;
      },
      error: (error: any) => {
        console.log(error);
        this.notificationService.onError("An error occurred while adding education info");
        this.refreshing = false;
      },
      complete: () => {
        console.log('Complete');
      }
    })
  }

  updateFormation() : void {
    console.log("this.formationForm?.value", this.formationForm?.value);
    this.formationService.updateFormation(this.formationForm?.value).subscribe({
      next: (response: Formation) => {
        this.notificationService.onInfo("Formation info updated successfully");
        this.getUserFormations();
        this.refreshing = false;
      },
      error: (error: any) => {
        console.log(error);
        this.notificationService.onError("An error occurred while updating education info");
        this.refreshing = false;
      },
      complete: () => {
        console.log('Complete');
      }
    })
  }

  addCompetence() : void {
    this.refreshing = true;
    this.competenceService.saveCompetence(this.competenceForm?.value, this.currentUser.id).subscribe({
      next: (response: any) => {
        this.notificationService.onSuccess("Skill added successfully");
        this.getUserCompetences();
        this.refreshing = false;
      },
      error: (error: any) => {
        console.log(error);
        this.notificationService.onError("An error occurred while adding skill");
        this.refreshing = false;
      },
      complete: () => {
        console.log('Complete');
      }
    })
  }

  deleteFormation( id : number) : void {
    console.log(id);
    this.refreshing = true;
    this.formationService.deleteFormation(id).subscribe({
      next: (response: any) => {
        this.notificationService.onError("Formation deleted successfully", "Suppression");
        this.getUserFormations();
        this.refreshing = false;
      },
      error: (error: any) => {
        console.log(error);
        this.notificationService.onError("An error occurred while deleting formation");
        this.refreshing = false;
      },
      complete: () => {
        console.log('Complete');
      }
    })
  }

  getUserFormations() : void {
    this.formationService.getAllFormationsByUserId(this.currentUser.id).subscribe({
      // mettre à jour les formations de l'utilisateur dans le local storage
      next: (response: Formation[]) => {
        this.currentUser.formations = response;
        localStorage.setItem('currentUser', JSON.stringify(this.currentUser));
        this.initCurrentExpFormLoiSocial(); // mettre à jour les formations de l'utilisateur dans le composant
      },
    })
  }

  getUserCompetences() : void {
    this.competenceService.getAllCompetencesByUserId(this.currentUser.id).subscribe({
      next: (response: any) => {
        this.currentUser.competences = response;
        localStorage.setItem('currentUser', JSON.stringify(this.currentUser));
        this.initCurrentExpFormLoiSocial(); // mettre à jour les compétences de l'utilisateur dans le composant
      },
    })
  }

  getUserExperiences() : void {
    this.experienceService.getAllExperiencesByUserId(this.currentUser.id).subscribe({
      next: (response: any) => {
        this.currentUser.experiences = response;
        localStorage.setItem('currentUser', JSON.stringify(this.currentUser));
        this.initCurrentExpFormLoiSocial(); // mettre à jour les expériences de l'utilisateur dans le composant
      },
    })
  }

  getUserLoisirs() : void {
    this.loisirService.getAllLoisirsByUserId(this.currentUser.id).subscribe({
      next: (response: any) => {
        this.currentUser.loisirs = response;
        localStorage.setItem('currentUser', JSON.stringify(this.currentUser));
        this.initCurrentExpFormLoiSocial(); // mettre à jour les loisirs de l'utilisateur dans le composant
      },
    })
  }

  getUserSocialMedias() : void {
    this.socialMediaService.getAllSocialMediaByUserId(this.currentUser.id).subscribe({
      next: (response: any) => {
        this.currentUser.socialMedias = response;
        localStorage.setItem('currentUser', JSON.stringify(this.currentUser));
        this.initCurrentExpFormLoiSocial();
      },
    })
  }

  openModal(data: any, currentForm: FormGroup) : void {
    if(data){
      currentForm.patchValue(data);
    }
  }

  getSelectedFormation(formation: Formation) : void {
    this.formationForm.patchValue(formation);
    this.selectedFormation = formation;
  }

  getSelectedCompetence(competence: any) : void {
    this.competenceForm.patchValue(competence);
    this.selectedCompetence = competence;
  }

  getSelectedExperience(experience: any) : void {
    this.experienceForm.patchValue(experience);
    this.selectedExperience = experience;
  }

  getSelectedLoisir(loisir: any) : void {
    this.loisirForm.patchValue(loisir);
    this.selectedLoisir = loisir;
    this.hideShowButtonUpdateDeleteLoisirForm = true;
  }

  getSelectedSocialMedia(socialMedia: any) : void {
    this.socialMediaForm.patchValue(socialMedia);
    this.selectedSocialMedia = socialMedia;
    this.hideShowButtonUpdateDeleteSocialMediaForm = true;
  }

  updateCompetence() : void {
    this.refreshing = true;
    this.competenceService.updateCompetence(this.competenceForm?.value).subscribe({
      next: (response: any) => {
        this.notificationService.onInfo("Skill updated successfully");
        this.getUserCompetences();
        this.refreshing = false;
      },
      error: (error: any) => {
        console.log(error);
        this.notificationService.onError("An error occurred while updating skill");
        this.refreshing = false;
      },
      complete: () => {
        console.log('Complete');
      }
    })
  }

  deleteCompetence(id: number) : void {
    this.refreshing = true;
    this.competenceService.deleteCompetence(id).subscribe({
      next: (response: any) => {
        this.notificationService.onError("Skill deleted successfully", 'Suppression');
        this.getUserCompetences();
        this.refreshing = false;
      },
      error: (error: any) => {
        console.log(error);
        this.notificationService.onError("An error occurred while deleting skill");
        this.refreshing = false;
      },
      complete: () => {
        console.log('Complete');
      }
    })
  }

  ajouterExperience() : void {
    this.refreshing = true;
    this.experienceService.saveExperience(this.experienceForm.value, this.currentUser.id).subscribe({
      next: (response: any) => {
        this.notificationService.onSuccess("Experience added successfully");
        this.getUserExperiences();
        this.refreshing = false;
      },
      error: (error: any) => {
        console.log(error);
        this.notificationService.onError("An error occurred while adding experience");
        this.refreshing = false;
      },
      complete: () => {
        console.log('Complete');
      }
    })
  }

  updateExperience() : void {
    this.refreshing = true;
    this.experienceService.updateExperience(this.experienceForm?.value).subscribe({
      next: (response: any) => {
        this.notificationService.onInfo("Experience updated successfully");
        this.getUserExperiences();
        this.refreshing = false;
      },
      error: (error: any) => {
        console.log(error);
        this.notificationService.onError("An error occurred while updating experience");
        this.refreshing = false;
      },
      complete: () => {
        console.log('Complete');
      }
    })
  }

  deleteExperience(id: number) : void {
    this.refreshing = true;
    this.experienceService.deleteExperience(id).subscribe({
      next: (response: any) => {
        this.notificationService.onError("Experience deleted successfully", 'Suppression');
        this.getUserExperiences();
        this.refreshing = false;
      },
      error: (error: any) => {
        console.log(error);
        this.notificationService.onError("An error occurred while deleting experience");
        this.refreshing = false;
      },
      complete: () => {
        console.log('Complete');
      }
    })
  }

  ajouterLoisir() : void {
    this.refreshing = true;
    this.loisirService.saveLoisir(this.currentUser.id, this.loisirForm?.value.hobby).subscribe({
      next: (response: any) => {
        this.notificationService.onSuccess("Hobby added successfully");
        this.getUserLoisirs();
        this.refreshing = false;
      },
      error: (error: any) => {
        console.log(error);
        this.notificationService.onError("An error occurred while adding hobby");
        this.refreshing = false;
      },
      complete: () => {
        console.log('Complete');
      }
    })
  }

  updateLoisir() : void {
    this.refreshing = true;
    this.loisirService.updateLoisir(this.loisirForm?.value).subscribe({
      next: (response: any) => {
        this.notificationService.onInfo("Hobby updated successfully");
        this.getUserLoisirs();
        this.refreshing = false;
      },
      error: (error: any) => {
        console.log(error);
        this.notificationService.onError("An error occurred while updating hobby");
        this.refreshing = false;
      },
      complete: () => {
        console.log('Complete');
      }
    })
  }

  deleteLoisir(id: number) : void {
    this.refreshing = true;
    this.loisirService.deleteLoisir(id).subscribe({
      next: (response: any) => {
        this.notificationService.onError("Hobby deleted successfully", 'Suppression');
        this.getUserLoisirs();
        this.refreshing = false;
      },
      error: (error: any) => {
        console.log(error);
        this.notificationService.onError("An error occurred while deleting hobby");
        this.refreshing = false;
      },
      complete: () => {
        console.log('Complete');
      }
    })
  }

  ajouterSocialMedia() : void {
    this.refreshing = true;
    this.socialMediaService.saveSocialMedia(this.currentUser.id, this.socialMediaForm?.value).subscribe({
      next: (response: any) => {
        this.notificationService.onSuccess("Social media added successfully");
        this.getUserSocialMedias();
        this.refreshing = false;
      },
      error: (error: any) => {
        console.log(error);
        this.notificationService.onError("An error occurred while adding social media");
        this.refreshing = false;
      },
      complete: () => {
        console.log('Complete');
      }
    })
  }

  updateSocialMedia() : void {
    this.refreshing = true;
    this.socialMediaService.updateSocialMedia(this.socialMediaForm?.value).subscribe({
      next: (response: any) => {
        this.notificationService.onInfo("Social media updated successfully");
        this.getUserSocialMedias();
        this.refreshing = false;
      },
      error: (error: any) => {
        console.log(error);
        this.notificationService.onError("An error occurred while updating social media");
        this.refreshing = false;
      },
      complete: () => {
        console.log('Complete');
      }
    })
  }

  deleteSocialMedia(id: number): void{
    this.refreshing = true;
    this.socialMediaService.deleteSocialMedia(id).subscribe({
      next: (response: any) => {
        this.notificationService.onError("Social media deleted successfully", 'Suppression');
        this.getUserSocialMedias();
        this.refreshing = false;
      },
      error: (error: any) => {
        console.log(error);
        this.notificationService.onError("An error occurred while deleting social media");
        this.refreshing = false;
      },
      complete: () => {
        console.log('Complete');
      }
    })
  }

  reBack() : void {
    this.hideShowButtonUpdateDeleteLoisirForm = false;
    this.loisirForm.reset();
  }

  reBackSocialMedia() : void {
    this.hideShowButtonUpdateDeleteSocialMediaForm = false;
    this.socialMediaForm.reset();
  }

  isEqual(option: string): boolean {
    return this.selectedSocialMedia && this.selectedSocialMedia.type.toLowerCase() === option.toLowerCase(); // Eviter les problème de la casse par exemple si on entre WhatsApp ou whatsapp ou WHATSAPP ou whatsApp c'est pareil.
  }
}
