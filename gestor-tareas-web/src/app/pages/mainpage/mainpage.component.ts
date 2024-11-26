import { Component, OnInit } from '@angular/core';
import { Task } from '@app/models/Task';


@Component({
  selector: 'app-mainpage',
  templateUrl: './mainpage.component.html',
  styleUrl: './mainpage.component.css',
  standalone: false
})
export class MainpageComponent implements OnInit {

  taskList: Task[] = [];

  ngOnInit(): void {

    this.taskList = [
      {
        id: "1a2b3c4d5e",
        titulo: "Comprar materiales de oficina",
        descripcion: "Adquirir papel, bolígrafos y carpetas para el nuevo proyecto.",
        fechacreacion: new Date(),
        fechavencimiento: new Date()
      },
      {
        id: "6f7g8h9i0j",
        titulo: "Reunión con el equipo de desarrollo",
        descripcion: "Discutir el progreso y los próximos pasos del proyecto.",
        fechacreacion: new Date(),
        fechavencimiento: null
      },
      {
        id: "k1l2m3n4o5",
        titulo: "Actualizar sitio web",
        descripcion: "Implementar el nuevo diseño y optimizar la velocidad de carga.",
        fechacreacion: new Date(),
        fechavencimiento: new Date(),
      }
    ]

  }

  addTask(){
    this.taskList.push(
      {
        id: null,
        titulo: "",
        descripcion: "",
        fechacreacion: new Date(),
        fechavencimiento: new Date()
      }
    )
  }

  editTask(task: Task){

  }

  deleteTask(index: number){

  }

}
