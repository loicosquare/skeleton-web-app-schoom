import {Utilisateur} from "./utilisateur.model";

export interface SocialMedia {
  id: number;
  utilisateur: Utilisateur;
  type: string;
  lien: string;
}
