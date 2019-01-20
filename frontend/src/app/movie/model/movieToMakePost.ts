import {CrewId} from '../../crew/model/crewId';

export class MovieToMakePost {
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
