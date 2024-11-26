export class Task{

  constructor(){
    this.titulo = "";
    this.descripcion = "";
    this.fechacreacion = new Date();
    this.fechavencimiento = null;
  }

  id: string;
  titulo: string;
  descripcion: string;
  fechacreacion: Date;
  fechavencimiento: Date;
}
