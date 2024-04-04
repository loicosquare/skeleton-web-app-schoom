import { Injectable } from '@angular/core';
import {LoginRequest} from "../models/utils/loginRequest.model";
import {catchError, Observable, tap, throwError} from "rxjs";
import {Utilisateur} from "../models/utilisateur.model";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class LoginRegisterService {

  public apiUrl: string = 'http://localhost:8081';

  login(loginRequest: LoginRequest): Observable<Utilisateur> {
    return this.httpClient.post<Utilisateur>(this.apiUrl + '/utilisateurs/login', loginRequest)
      .pipe(
        tap(console.log),
        catchError(this.handleError)
      );
  }

  constructor(private httpClient : HttpClient) { }
  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(`An error occurred - Error code: ${error.status}`);
  }
}
