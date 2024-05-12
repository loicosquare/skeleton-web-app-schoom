import { Component, OnInit } from "@angular/core"
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from "@angular/forms"
import { EditorComponent } from "@tinymce/tinymce-angular"
import { NgIf } from "@angular/common"
import { UtilisateurService } from "../../services/utilisateur.service"
import { Utilisateur } from "../../models/utilisateur.model"
import { NotificationService } from "../../services/notification.service"

@Component({
  selector: 'contact-me',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    EditorComponent,
    FormsModule,
    NgIf,
  ],
  templateUrl: './contact-me.component.html',
  styleUrl: './contact-me.component.scss'
})
export class ContactMeComponent implements OnInit{
  contactMeForm!: FormGroup;
  public refreshing! : boolean;
  public currentUser!: Utilisateur;

  constructor(private utilisateurService: UtilisateurService, private notificationService: NotificationService) {
    this.contactMeForm = new FormGroup({
      id: new FormControl(''),
      city: new FormControl('', Validators.required),
      state: new FormControl('', Validators.required),
      country: new FormControl('', Validators.required),
      email: new FormControl('', Validators.required),
      message: new FormControl('', Validators.required),
      title: new FormControl('', Validators.required),
      phone: new FormControl('', Validators.required),
    });
  }

  ngOnInit(): void {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser') || '{}');
  }


  contactMe() {
    this.refreshing = true;
    if (this.contactMeForm.valid) {
      this.utilisateurService.sendEmail(this.currentUser, this.contactMeForm.value).subscribe(
        {
          next: (data: boolean) => {
            this.notificationService.onSuccess('Email sent successfully');
            this.refreshing = false;
            this.contactMeForm.reset();
          },
          error: (error) => {
            console.log('Error sending email');
            this.notificationService.onError('Error sending email');
            this.refreshing = false;
          }
        }
      );
    }
  }
}
