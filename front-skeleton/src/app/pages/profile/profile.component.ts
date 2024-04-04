import {Component, OnInit} from '@angular/core';
import { Form, FormControl, FormGroup, FormsModule, NgForm, ReactiveFormsModule, Validators } from "@angular/forms"
import {Utilisateur} from "../../models/utilisateur.model";
import { UtilisateurRequest } from "../../models/utils/utilisateurRequest.model"
import { UtilisateurService } from "../../services/utilisateur.service"
import { NotificationService } from "../../services/notification.service"
import { NgIf } from "@angular/common"
import { FormationService } from "../../services/formation.service"
import { ExperienceService } from "../../services/experience.service"
import { LoisirService } from "../../services/loisir.service"
import { SocialMediaService } from "../../services/social-media.service"
import { CompetenceService } from "../../services/competence.service"
import { Formation } from "../../models/formation.model"

@Component({
  selector: 'profile',
  standalone: true,
  imports: [
    FormsModule,
    ReactiveFormsModule,
    NgIf,
  ],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.scss'
})
export class UserComponent implements OnInit{
  currentUser! : Utilisateur | any;
  currentFormation! : Formation | Formation[];
  userInfoForm : FormGroup;
  formationForm : FormGroup;
  buttonFormationTitle = '';
  public refreshing! : boolean;

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
      //id: new FormControl(''),
      diplome: new FormControl('', Validators.required),
      domaineEtudes: new FormControl('', Validators.required),
      ecole: new FormControl('', Validators.required),
      lieu: new FormControl('', Validators.required),
      anneeDebut: new FormControl('', Validators.required),
      anneeFin: new FormControl('', Validators.required)
    });
  }

  ngOnInit(): void {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser') || '{}');
    this.userInfoForm.patchValue(this.currentUser);
    this.currentFormation =  this.currentUser.formations;

    //Afficher le texte du bouton en fonction du contenu de l'objet currentFormation
    if (this.currentFormation) {
      this.buttonFormationTitle = 'Update formation';
      this.formationForm.patchValue(this.currentFormation);
    } else {
      this.buttonFormationTitle = 'Ajouter une formation';
    }
  }

  updateUserInfo() {
    console.log(this.userInfoForm?.value);
    this.refreshing = true;
    this.utlisateurService.updateUtilisateur(this.userInfoForm?.value).subscribe({
      next: (response: Utilisateur) => {
        // remplacer l'utilisateur du local storage par le nouvel utilisateur mis à jour
        localStorage.setItem('currentUser', JSON.stringify(response));
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

  updateOrAddFormation() {
    console.log(this.formationForm?.value);
    this.refreshing = true;
    if(this.buttonFormationTitle === 'Ajouter une formation') {
      this.AjouterFormation();
    }else {

    }
  }

  AjouterFormation() : void {
    this.formationService.saveFormation(this.currentUser.id, this.formationForm?.value).subscribe({
      next: (response: Formation) => {
        this.notificationService.onSuccess("Formation info added successfully");
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

  UpdateFormation() : void {
    this.formationService.updateFormation(this.formationForm?.value).subscribe({
      next: (response: Formation) => {
        this.notificationService.onSuccess("Formation info updated successfully");
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
}
