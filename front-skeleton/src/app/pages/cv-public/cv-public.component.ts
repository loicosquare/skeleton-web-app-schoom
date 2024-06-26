import {Component, OnInit} from '@angular/core';
import {Utilisateur} from "../../models/utilisateur.model";
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from "@angular/forms"
import { NgForOf, NgIf } from "@angular/common"
import { NotificationService } from "../../services/notification.service"
import { MatListItem, MatNavList } from "@angular/material/list"
import { Router, RouterLink, RouterLinkActive } from "@angular/router"
import { ColorPickerModule } from "ngx-color-picker"

@Component({
  selector: 'cv-public',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    NgIf,
    NgForOf,
    MatListItem,
    MatNavList,
    RouterLink,
    RouterLinkActive,
    ColorPickerModule,
    FormsModule,
  ],
  templateUrl: './cv-public.component.html',
  styleUrl: './cv-public.component.scss'
})
export class CvPublicComponent implements OnInit {

  currentUser : Utilisateur | any;
  myForm: FormGroup;
  choosedTheme! : number;
  currentFormation! : any;
  currentExperience! : any;
  currentCompetence! : any;
  currentLoisir! : any;
  currentSocialMedias! : any;
  filename!: string;
  selectedColor: string = 'linear-gradient(to right, #4a0a67, #d50000)';

  constructor(private formBuilder: FormBuilder,
              private notificationService: NotificationService,
              private router: Router) {
    this.myForm = this.formBuilder.group({
      theme: ['']
    });
  }

  ngOnInit(): void {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser') || '{}');
    this.initCurrentExpFormLoiSocial();
    this.filename = 'CV_' + this.currentUser.username + '_' + this.currentUser.name + '.json';
  }
  initCurrentExpFormLoiSocial() : void {
    if(this.currentUser) {
      this.currentFormation =  this.currentUser.formations;
      this.currentCompetence = this.currentUser.competences;
      this.currentExperience = this.currentUser.experiences;
      this.currentLoisir = this.currentUser.loisirs;
      this.currentSocialMedias = this.currentUser.socialMedias;
    }
  }


  onThemeChange(event: any) {
    this.choosedTheme = event.target.value;
    this.notificationService.onWarning("Vous avez choisi le thème " + this.choosedTheme);
  }

  exportToJson(): void {
    alert("Téléchargement du fichier JSON");

    // On transforme l'objet en JSON et on le télécharge en tant que fichier
    const json = JSON.stringify(this.currentUser, null, 2); // 2 espaces pour l'indentation
    const blob = new Blob([json], { type: 'application/json' });

    // Création d'une URL pour le fichier
    const url = window.URL.createObjectURL(blob);

    // Création d'un lien pour le téléchargement
    const a = document.createElement('a');
    a.href = url;
    a.download = this.filename;

    // Ajout du lien au DOM et clic dessus pour le téléchargement
    document.body.appendChild(a);
    a.click();

    // Nettoyage de l'URL et suppression du lien
    window.URL.revokeObjectURL(url);

    // Suppression du lien du DOM après le téléchargement
    document.body.removeChild(a);

    this.notificationService.onInfo("Le fichier JSON a été téléchargé avec succès");
  }

  contactMe() : void {
    this.router.navigate(['/contact-me']);
  }

  updateBackgroundColor(color: string) {
    this.selectedColor = color;
  }
}
