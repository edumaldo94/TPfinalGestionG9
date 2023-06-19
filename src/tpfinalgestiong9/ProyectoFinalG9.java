package tpfinalgestiong9;

import AccesoADatos.ComentarioData;
import AccesoADatos.Conexion;
import AccesoADatos.EquipoData;
import AccesoADatos.EquipoMiembroData;
import AccesoADatos.MiembroData;
import AccesoADatos.ProyectoData;
import AccesoADatos.TareaData;
import Entidades.Comentario;
import Entidades.Equipo;
import Entidades.EquipoMiembro;
import Entidades.Miembro;
import Entidades.Proyecto;
import Entidades.Tarea;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProyectoFinalG9 {

    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in).useDelimiter("\n");
        Conexion.getConexion();
        int op = 0, op2 = 0, op3 = 0;
        String fechaDefault = "1900-01-01";
        LocalDate fecha = LocalDate.parse(fechaDefault);
        LocalDate fechaE = LocalDate.parse(fechaDefault);
        LocalDate fechaME = LocalDate.parse(fechaDefault);
        LocalDate fechaCRE = LocalDate.parse(fechaDefault);
        LocalDate fechaCIE = LocalDate.parse(fechaDefault);
        LocalDate fechaAV = LocalDate.parse(fechaDefault);
        ProyectoData proyD = new ProyectoData();
        EquipoData equipoD = new EquipoData();
        MiembroData miembroD = new MiembroData();
        TareaData tareaD = new TareaData();
        ComentarioData comentD = new ComentarioData();
        EquipoMiembroData equipoMiembroD = new EquipoMiembroData();
        boolean veri = true, veri2 = true, veri3 = true;
        do {
            System.out.println("CREACION DE PROYECTOS");
            System.out.println("1- Crear un proyecto"); //P
            System.out.println("2- Crear un equipo"); //E
            System.out.println("3- Crear un miembro"); //M
            System.out.println("4- Añadir miembro a un equipo"); //M
            System.out.println("5- Asignar una tarea a un miembro de un equipo");//T
            System.out.println("6- Actualizar estado de las tareas");//T
            System.out.println("7- Registrar avances en las tareas");
            System.out.println("8- Consultar proyectos y tareas");
            System.out.println("9- Consultar información del equipo"); //E
            System.out.println("10-Activar/Desactivar");
            System.out.println("11-Eliminar Miembro a un MiembroEquipo");
            System.out.println("0- Salir");
            System.out.println("Ingrese una opcion");
            op = leer.nextInt();
            switch (op) {
                case 1:
                    System.out.println("Ingrese el nombre del proyecto");
                    String nom = leer.next();
                    System.out.println("Ingrese la descripcion");
                    String des = leer.next();
                    System.out.println("Ingrese la fecha de inicio");
                    System.out.println("Año: ");
                    int anio = leer.nextInt();
                    System.out.println("Mes: ");
                    int mes = leer.nextInt();
                    System.out.println("Dia: ");
                    int dia = leer.nextInt();
                    fecha = LocalDate.of(anio, mes, dia);
                    Proyecto proy = new Proyecto(nom, des, fecha, true);
                    proyD.guardarProyecto(proy);
                    break;

                case 2:
                    System.out.println("Ingrese el nombre del equipo");
                    String nomE = leer.next();
                    System.out.println("Ingrese el ID del proyecto asignado");
                    int idE = leer.nextInt();
                    System.out.println("Ingrese la fecha de creacion");
                    System.out.println("Año: ");
                    int anioE = leer.nextInt();
                    System.out.println("Mes: ");
                    int mesE = leer.nextInt();
                    System.out.println("Dia: ");
                    int diaE = leer.nextInt();
                    fechaE = LocalDate.of(anioE, mesE, diaE);
                    Proyecto proy1 = proyD.buscarProyectoPorId(idE);
                    Equipo equipo = new Equipo(proy1, nomE, fechaE, true);
                    equipoD.guardarEquipo(equipo);
                    break;
                case 3:
                    System.out.println("Ingrese el apellido");
                    String apM = leer.next();
                    System.out.println("Ingrese el nombre");
                    String nomM = leer.next();
                    System.out.println("Ingrese el DNI");
                    int dniM = leer.nextInt();
                    Miembro miembro = new Miembro(dniM, nomM, apM, true);
                    miembroD.guardarMiembro(miembro);
                    break;
                case 4:
                    System.out.println("Ingrese el ID del equipo");
                    int idEquipo = leer.nextInt();
                    System.out.println("Ingrese el ID del miembro");
                    int idMiembro = leer.nextInt();
                    System.out.println("Ingrese la fecha de incorporacion");
                    System.out.println("Año: ");
                    int anioME = leer.nextInt();
                    System.out.println("Mes: ");
                    int mesME = leer.nextInt();
                    System.out.println("Dia: ");
                    int diaME = leer.nextInt();
                    fechaME = LocalDate.of(anioME, mesME, diaME);
                    Equipo equipoAux = equipoD.buscarEquipoPorId(idEquipo);
                    Miembro miembroAux = miembroD.buscarMiembroPorId(idMiembro);
                    EquipoMiembro equipoMiembro = new EquipoMiembro(equipoAux, miembroAux, fechaME);
                    equipoMiembroD.guardarEquipoMiembro(equipoMiembro);
                    break;
                case 5:
                    System.out.println("Ingrese el nombre de la tarea");
                    String nombreT = leer.next();
                    System.out.println("Ingrese el ID EquipoMiembro");
                    int idEqMiT = leer.nextInt();
                    EquipoMiembro equipoMiembroAux = equipoMiembroD.buscarEquipoMiembroPorId(idEqMiT);
                    System.out.println("Ingrese la fecha de creacion");
                    System.out.println("Año: ");
                    int anioCRE = leer.nextInt();
                    System.out.println("Mes: ");
                    int mesCRE = leer.nextInt();
                    System.out.println("Dia: ");
                    int diaCRE = leer.nextInt();
                    fechaCRE = LocalDate.of(anioCRE, mesCRE, diaCRE);
                    System.out.println("Ingrese la fecha de cierre");
                    System.out.println("Año: ");
                    int anioCIE = leer.nextInt();
                    System.out.println("Mes: ");
                    int mesCIE = leer.nextInt();
                    System.out.println("Dia: ");
                    int diaCIE = leer.nextInt();
                    fechaCIE = LocalDate.of(anioCIE, mesCIE, diaCIE);
                    int comparacion = fechaCIE.compareTo(fechaCRE);
                    if (comparacion < 0 || comparacion == 0) {
                        System.out.println("Fecha mal ingresada, supera la fecha de cierre");
                        break;
                    } else {
                        Tarea tarea = new Tarea(equipoMiembroAux, nombreT, fechaCIE, fechaCRE, 0);
                        //Pendiente 0 - En Proceso 1 - Completada 2
                        tareaD.guardarTarea(tarea);
                    }
                    break;
                case 6:
                    do {
                        System.out.println("Ingrese la ID de la tarea");
                        int idTarea = leer.nextInt();
                        System.out.println("Ingrese el nuevo estado");
                        System.out.println("1- Pendiente");
                        System.out.println("2- En Proceso");
                        System.out.println("3- Completada");
                        System.out.println("Ingrese una opcion");
                        op2 = leer.nextInt();
                        switch (op2) {
                            case 1:
                                tareaD.actualizarEstadoTarea(0, idTarea);
                                veri2 = false;
                                break;
                            case 2:
                                tareaD.actualizarEstadoTarea(1, idTarea);
                                veri2 = false;
                                break;
                            case 3:
                                tareaD.actualizarEstadoTarea(2, idTarea);
                                veri2 = false;
                                break;
                            default:
                                System.out.println("Opcion incorrecta");
                        }
                    } while (veri2);
                    break;
                case 7:
                    System.out.println("Ingrese la ID de la tarea");
                    int idTareaCom = leer.nextInt();
                    Tarea tarrea = tareaD.buscarTareaPorId(idTareaCom);
                    System.out.println("Ingrese el comentario");
                    String comentario = leer.next();
                    System.out.println("Ingrese la fecha del avance");
                    System.out.println("Año: ");
                    int anioAV = leer.nextInt();
                    System.out.println("Mes: ");
                    int mesAV = leer.nextInt();
                    System.out.println("Dia: ");
                    int diaAV = leer.nextInt();
                    fechaAV = LocalDate.of(anioAV, mesAV, diaAV);
                    Comentario coment = new Comentario(comentario, fechaAV, tarrea);
                    comentD.guardarComentario(coment);
                    break;
                case 8:
                    List<Proyecto> listP = new ArrayList<>();

                    listP = proyD.listarProyectos();

                    for (Proyecto proyect : listP) {
                        System.out.println(proyect);
                    }
                    System.out.println("Ingrese id de Proyecto para corroborar sus tareas: ");
                    int id = leer.nextInt();
                    tareaD.areasDeproyecto(id);
                    break;
                case 9:
                    List<Equipo> listEqui = new ArrayList<>();
                    listEqui = equipoD.listaEquipo();
                    for (Equipo equipe : listEqui) {
                        System.out.println(equipe);
                    }
                    System.out.println("Ingrese el iD del Equipo o Grupo");
                    int idEq = leer.nextInt();
                    equipoD.infoDeEquipo(idEq);
                    System.out.println("");
                    break;
                case 10:
                    do {
                        System.out.println("1-Activar o Desactivar un Proyecto");
                        System.out.println("2-Activar o Desactivar un Equipo");
                        System.out.println("3-Activar o Desactivar un Miembro");
                        System.out.println("ingrese una opcion");
                        op3 = leer.nextInt();
                        switch (op3) {
                            case 1:
                                System.out.println("Ingrese la ID del proyecto");
                                int idProy = leer.nextInt();
                                proyD.activarDesactivar(idProy);
                                veri3 = false;
                                break;
                            case 2:
                                System.out.println("Ingrese la ID del Equipo");
                                int idEquipoo = leer.nextInt();
                                equipoD.activarDesactivar(idEquipoo);
                                veri3 = false;
                                break;
                            case 3:
                                System.out.println("Ingrese la ID del Miembro");
                                int idMiembroo = leer.nextInt();
                                miembroD.activarDesactivar(idMiembroo);
                                veri3 = false;
                                break;
                            default:
                                System.out.println("Opcion incorrecta");
                        }
                    } while (veri3);
                    break;
                case 11:
                    System.out.println("Ingrese la ID del MiembroEquipo");
                    int idMiEqq = leer.nextInt();
                    equipoMiembroD.borrarMiembroEq(idMiEqq);
                    break;
                case 0:
                    veri = false;
                    break;
                default:
                    System.out.println("Opcion incorrecta");
            }
        } while (veri);
        System.out.println("");
    }
}
