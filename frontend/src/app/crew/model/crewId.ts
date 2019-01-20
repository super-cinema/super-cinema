export class CrewId {
  id: number;
  name?: string;
  surname?: string;
  crewRoles?: string[] = [];

  constructor(id: number) {
    this.id = id;

  }
}
