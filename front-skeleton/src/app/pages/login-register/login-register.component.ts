import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, NgForm, ReactiveFormsModule, Validators} from "@angular/forms";
import {LoginRegisterService} from "../../services/login-register.service";
import {Utilisateur} from "../../models/utilisateur.model";
import {Router} from "@angular/router";
import {NgIf} from "@angular/common";
import { NotificationService } from "../../services/notification.service"

@Component({
  selector: 'login-register',
  standalone: true,
  imports: [
    FormsModule,
    ReactiveFormsModule,
    NgIf
  ],
  templateUrl: './login-register.component.html',
  styleUrl: './login-register.component.scss'
})
export class LoginRegisterComponent  implements OnInit{

  loginForm: FormGroup;
  registerForm: FormGroup;
  loggedUser!: Utilisateur;
  public refreshing! : boolean;

  constructor(private formBuilder: FormBuilder,
              private loginRegisterService: LoginRegisterService,
              private notificationService: NotificationService,
              private router : Router) {

    this.loginForm = this.formBuilder.group({
      email: ['', Validators.required],
      password: ['', Validators.required]
    });

    this.registerForm = this.formBuilder.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });

    this.loggedUser = {} as Utilisateur;
  }

  ngOnInit(): void {
  }

  login(loginForm: FormGroup) {
    console.log("Logging in...", loginForm.value);
    this.refreshing = true;
    this.loginRegisterService.login(loginForm.value).subscribe(
      (response: Utilisateur) => {
        this.loggedUser = response;
        this.refreshing = false;
        this.notificationService.onSuccess("Login success");

        // Supprimez l'objet utilisateur du localStorage s'il existe
        if(localStorage.getItem('currentUser') != null){
          localStorage.removeItem('currentUser');
        }

        console.log(response);
      },
      (error) => {
        console.error("Login error:", error);
        this.refreshing = false;
        this.notificationService.onError("Login Failed");
      },
      () => {
        console.log("Login completed");
        this.refreshing = false;
        this.notificationService.onInfo("Login completed");

        // Stockez l'objet utilisateur dans le localStorage
        localStorage.setItem('currentUser', JSON.stringify(this.loggedUser));

        //On redirige l'utilisateur vers la page du cv si le login est réussi
        if(localStorage.getItem('currentUser') != null){
          this.router.navigate(['/cv-public']).then(r => console.log(r));
        }
      }
    );
  }
}
