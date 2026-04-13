package com.example.funcionallab
import android.util.Log

data class Estudiante(val nombre: String, val nota: Double)

//Extension Function1
fun String.esNombreValido(): Boolean = this.isNotBlank() && this.length > 2

//Extension Function2
fun Double.esAprobado(): Boolean = this >= 7.0

//Extension Property
val Estudiante.estado: String
    get() = if (nota.esAprobado()) "Aprobado" else "Reprobado"

//Higher-order function 1
fun procesarEstudiantes (estudiantes: List<Estudiante>, operacion: (Estudiante) -> Unit)
{
    for (e in estudiantes){
        operacion(e)
    }
}

//Higher-order function 2 (inline)
inline fun filtrarEstudiantes (estudiantes: List<Estudiante>, criterio: (Estudiante) -> Boolean): List <Estudiante>{
    return estudiantes.filter(criterio)
}

//Funcion principal para pruebas
fun ejecutarLaboratorio() {
    val lista = listOf(
        Estudiante("Ana", 8.5),
        Estudiante("Luis", 6.2),
        Estudiante("Maria", 9.0),
        Estudiante("Pedro", 5.8)
    )

    Log.d("com.example.funcionallab", "=== Todos los estudiantes ===")
    procesarEstudiantes(lista) {
        Log.d("com.example.funcionallab", "${it.nombre} - Nota: ${it.nota} - Estado: ${it.estado}")
    }

    Log.d("com.example.funcionallab", "=== Estudiantes aprobados ===")
    val aprobados = filtrarEstudiantes(lista) { it.nota.esAprobado() }

    aprobados.forEach {
        Log.d("com.example.funcionallab", "${it.nombre} (${it.nota})")
    }
}