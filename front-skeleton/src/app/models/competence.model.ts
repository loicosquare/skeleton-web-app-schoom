import {Utilisateur} from "./utilisateur.model";

export interface Competence {
  id: number;
  utilisateur: any
  competence: string;
  niveau: number;
}
