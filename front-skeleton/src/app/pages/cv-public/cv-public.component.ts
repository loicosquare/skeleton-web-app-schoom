import {Component, OnInit} from '@angular/core';
import {Formation} from "../../models/formation.model";
import {Competence} from "../../models/competence.model";
import {Loisir} from "../../models/loisir.model";
import {Experience} from "../../models/experience.model";
import {Utilisateur} from "../../models/utilisateur.model";
import {FormBuilder, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {NgIf} from "@angular/common";
import { NotificationService } from "../../services/notification.service"

@Component({
  selector: 'cv-public',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    NgIf
  ],
  templateUrl: './cv-public.component.html',
  styleUrl: './cv-public.component.scss'
})
export class CvPublicComponent implements OnInit {

  currentUser : Utilisateur | undefined;
  myForm: FormGroup;
  choosedTheme! : number;

  // Exemples d'expériences
  experiences: Experience[] = [
    {
      id: 1,
      titre: "Développeur Web",
      entreprise: "ABC Solutions",
      lieu: "Paris, France",
      description: "Conception et développement de sites Web",
      dateDebut: new Date("2020-01-01"),
      dateFin: new Date("2021-06-30"),
      utilisateur: 1
    },
  ];

  // Exemples de loisirs
  loisirs: Loisir[] = [
    {
      id: 1,
      hobby: "Voyager",
      utilisateur: 1
    },
  ];

  // Exemples de compétences
  competences: Competence[] = [
    {
      id: 1,
      competence: "HTML",
      niveau: 5,
      utilisateur: 1
    },
  ];

  // Exemples de formations
  formations: Formation[] = [
    {
      id: 1,
      diplome: "Licence en Informatique",
      domaineEtudes: "Développement Web",
      ecole: "Université XYZ",
      lieu: "Paris, France",
      anneeDebut: 2017,
      anneeFin: 2020,
      utilisateur: 1
    },
  ];
  constructor(private formBuilder: FormBuilder,
              private notificationService: NotificationService,) {
    this.myForm = this.formBuilder.group({
      theme: ['']
    });
  }

  ngOnInit(): void {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser') || '{}');
  }

  onThemeChange(event: any) {
    this.choosedTheme = event.target.value;
    this.notificationService.onWarning("Vous avez choisi le thème " + this.choosedTheme);
    console.log(this.choosedTheme);
  }
}
