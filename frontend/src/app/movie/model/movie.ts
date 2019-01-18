import {Crew} from '../../crew/model/crew';
import {CrewId} from "../../crew/model/crewId";

export class Movie {
  id;
  title: string;
  duration;
  productionCountry: string;
  productionYear: string;
  directors: CrewId[];
  cast?: CrewId[];
  movieShow?: string;
  types: string[] = [];
}
