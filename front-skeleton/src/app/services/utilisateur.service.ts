import { Injectable } from '@angular/core';
import { catchError, Observable, tap, throwError } from "rxjs"
import { Competence } from "../models/competence.model"
import { HttpClient, HttpErrorResponse, HttpHeaders, HttpParams } from "@angular/common/http"
import { UtilisateurRequest } from "../models/utils/utilisateurRequest.model"
import { Utilisateur } from "../models/utilisateur.model"
import { ContactForm } from "../models/contactForm.model"

@Injectable({
  providedIn: 'root'
})
export class UtilisateurService {

  private apiUrl = 'http://localhost:8081/utilisateurs';
  private apiUrlEmail = 'http://localhost:8081/emails';

  constructor(private http: HttpClient) { }

  getUtilisateurs(): Observable<Utilisateur[]> {
    return this.http.get<Utilisateur[]>(this.apiUrl+"/all")
      .pipe(
        tap((utilisateurs: Utilisateur[]) => {
          console.log('Utilisateurs récupérés avec succès:', utilisateurs);
        }),
        catchError(this.handleError)
      );
  }

  updateUtilisateur(utilisateurRequest: UtilisateurRequest): Observable<Utilisateur> {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this.http.post<Utilisateur>(`${this.apiUrl}/add`, utilisateurRequest, { headers })
      .pipe(
        tap((updatedUser: Utilisateur) => {
          console.log('Utilisateur MAJ avec succès:', updatedUser);
        }),
        catchError(this.handleError)
      );
  }

  /*sendEmail(utilisateur: UtilisateurRequest, contactForm: ContactForm): Observable<boolean> {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this.http.post<boolean>(
      `${this.apiUrlEmail}/sendEmail`,
      { utilisateur, contactForm },
      { headers }
    ).pipe(
      tap((isEmailSent: boolean) => {
        console.log('Email envoyé avec succès:', isEmailSent);
      }),
      catchError(this.handleError)
    );
  }*/

  sendEmail(utilisateur: UtilisateurRequest, contactForm: ContactForm): Observable<boolean> {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    const paramBody = {
      id: utilisateur.id,
      username: utilisateur.username,
      email: utilisateur.email,
      password: utilisateur.password,
      profilePicture: utilisateur.profilePicture,
      name: utilisateur.name,
      telephone: utilisateur.telephone,
      pays: utilisateur.pays,
      ville: utilisateur.ville,
      /*email: utilisateur.email,
      username: utilisateur.username,*/
      city: contactForm.city,
      country: contactForm.country,
      state: contactForm.state,
      title: contactForm.title,
      message: contactForm.message,
      phone: contactForm.phone
    }

    let params = new HttpParams()
      .set('id', utilisateur.id)
      .set('username', utilisateur.username)
      .set('email', utilisateur.email)
      .set('password', utilisateur.password)
      .set('profilePicture', utilisateur.profilePicture)
      .set('name', utilisateur.name)
      .set('telephone', utilisateur.telephone)
      .set('pays', utilisateur.pays)
      .set('ville', utilisateur.ville)

      .set('email', utilisateur.email)
      .set('username', utilisateur.username)
      .set('city', contactForm.city)
      .set('country', contactForm.country)
      .set('state', contactForm.state)
      .set('title', contactForm.title)
      .set('message', contactForm.message)
      .set('phone', contactForm.phone);

    return this.http.post<boolean>(
      `${this.apiUrlEmail}/sendEmail`,
      {paramBody},
      { headers }
    ).pipe(
      tap((isEmailSent: boolean) => {
        console.log('Email envoyé avec succès:', isEmailSent);
      }),
      catchError(this.handleError)
    );
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`An error occurred - Error code: ${error.status}`);
  }
}
