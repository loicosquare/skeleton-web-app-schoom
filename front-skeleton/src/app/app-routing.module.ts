import { NgModule } from "@angular/core"
import { RouterModule, Routes } from "@angular/router"
import { HomeComponent } from "home/home.component"
import { StudentsComponent } from "students/students.component"
import { StudentsResolver } from "students/students.resolver"
import { StudentDetailsComponent } from "students/student-details/student-details.component"
import { StudentDetailsResolver } from "students/student-details/student-details.resolver"
import { MajorsComponent } from "majors/majors.component"
import { MajorsResolver } from "majors/majors.resolver"
import { MajorStudentsResolver } from "majors/major-students/major-students.resolver"
import { MajorStudentsComponent } from "majors/major-students/major-students.component"
import {UserComponent} from "./pages/profile/profile.component";
import {LoginRegisterComponent} from "./pages/login-register/login-register.component";
import {CvPublicComponent} from "./pages/cv-public/cv-public.component";
import { CvThequeComponent } from "./pages/cv-theque/cv-theque.component"
import { ContactMeComponent } from "./pages/contact-me/contact-me.component"

const routes: Routes = [
  { path: "", component: HomeComponent },
  {
    path: "etudiants",
    component: StudentsComponent,
    resolve: {
      students: StudentsResolver,
    },
  },
  {
    path: "details-etudiant/:id",
    component: StudentDetailsComponent,
    resolve: {
      student: StudentDetailsResolver,
    },
  },
  {
    path: "filieres",
    component: MajorsComponent,
    resolve: {
      majors: MajorsResolver,
    },
  },
  {
    path: "etudiants-filiere/:id",
    component: MajorStudentsComponent,
    resolve: {
      studentsFromMajor: MajorStudentsResolver,
    },
  },
  {
    path: "profile",
    component: UserComponent,
  },
  {
    path: "login-register",
    component: LoginRegisterComponent,
  },
  {
    path: "cv-public",
    component: CvPublicComponent,
  },
  {
    path: "cv-theque",
    component: CvThequeComponent,
  },
  {
    path: "contact-me",
    component: ContactMeComponent,
  },
  { path: "**", redirectTo: "" },
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
