import { Component, OnInit } from "@angular/core"
import { Link } from "models/links.model"
import { Utilisateur } from "../models/utilisateur.model"

@Component({
  selector: "navbar",
  templateUrl: "./navbar.component.html",
  styleUrls: ["./navbar.component.scss"],
})
export class NavbarComponent implements OnInit{
  links: Link[] = []
  currentUser!: Utilisateur;
  public isCvThequeChoosed!: string;

  constructor() {
    this.links.push({ name: "Profil", href: "/profile" })
    this.links.push({ name: "Mon CV", href: "/cv-public" })
  }

  ngOnInit(): void {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser') || '{}');
    this.isCvThequeChoosed = localStorage.getItem('cvThequeChoosed')!.toString();
    console.log('cvThequeChoosed', this.isCvThequeChoosed);
  }
}
