import { Component, OnInit } from "@angular/core"
import { UtilisateurService } from "../../services/utilisateur.service"
import { NgForOf, NgOptimizedImage } from "@angular/common"
import { Utilisateur } from "../../models/utilisateur.model"
import { Router } from "@angular/router"

@Component({
  selector: 'cv-theque',
  standalone: true,
  imports: [
    NgForOf,
    NgOptimizedImage,
  ],
  templateUrl: './cv-theque.component.html',
  styleUrl: './cv-theque.component.scss'
})
export class CvThequeComponent implements OnInit {
  public usersList: any[] = [];
  public choosedUser!: Utilisateur;

  constructor(private utilisateurService: UtilisateurService, private router: Router) {
  }

  ngOnInit(): void {
    this.getUsersList();
  }

  getUsersList(): void {
    this.utilisateurService.getUtilisateurs().subscribe({
      next: (users: any) => {
        this.usersList = users;
      },
      error: (error: any) => {
        console.error('Erreur lors de la récupération des utilisateurs:', error);
      }
    })
  }

  chooseUser(id : number): void {
    this.choosedUser = this.usersList.find(user => user.id === id);
    localStorage.removeItem('currentUser');
    localStorage.setItem('currentUser', JSON.stringify(this.choosedUser));
    this.router.navigate(['/cv-public']).then(() => {
      console.log("Navigated to cv-public");
    });
  }

}
