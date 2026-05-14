package co.saludconecta.principal;

import co.saludconecta.dao.CitaDao;
import co.saludconecta.dao.PacienteDao;
import co.saludconecta.modelo.Cita;
import co.saludconecta.modelo.Paciente;

import java.util.List;

/**
 * Clase principal de prueba del módulo SaludConecta.
 * Demuestra las cuatro operaciones CRUD sobre las entidades
 * Paciente y Cita mediante conexión JDBC.
 */
public class Main {

    public static void main(String[] args) {

        PacienteDao pacienteDao = new PacienteDao();
        CitaDao     citaDao     = new CitaDao();

        // ── 1. INSERTAR paciente ────────────────────────────────────
        System.out.println("=== INSERTAR PACIENTE ===");
        Paciente nuevoPaciente = new Paciente(
            0, "Laura Sofía", "Ramírez Torres",
            "CC", "1020304050", "1998-04-15",
            "laura.ramirez@email.com", "3001234567",
            "Calle 45 # 12-30, Bogotá"
        );
        boolean insercionExitosa = pacienteDao.insertar(nuevoPaciente);
        System.out.println("Paciente insertado: " + insercionExitosa);

        // ── 2. CONSULTAR TODOS los pacientes ────────────────────────
        System.out.println("\n=== CONSULTAR TODOS LOS PACIENTES ===");
        List<Paciente> listaPacientes = pacienteDao.consultarTodos();
        listaPacientes.forEach(System.out::println);

        // ── 3. CONSULTAR PACIENTE por ID ────────────────────────────
        System.out.println("\n=== CONSULTAR PACIENTE POR ID ===");
        Paciente pacienteEncontrado = pacienteDao.consultarPorId(1);
        if (pacienteEncontrado != null) {
            System.out.println("Encontrado: " + pacienteEncontrado);
        } else {
            System.out.println("Paciente no encontrado.");
        }

        // ── 4. ACTUALIZAR paciente ──────────────────────────────────
        System.out.println("\n=== ACTUALIZAR PACIENTE ===");
        if (pacienteEncontrado != null) {
            pacienteEncontrado.setEmail("laura.nueva@email.com");
            pacienteEncontrado.setTelefono("3119876543");
            boolean actualizacionExitosa = pacienteDao.actualizar(pacienteEncontrado);
            System.out.println("Paciente actualizado: " + actualizacionExitosa);
        }

        // ── 5. INSERTAR cita ────────────────────────────────────────
        System.out.println("\n=== INSERTAR CITA ===");
        Cita nuevaCita = new Cita(
            0, "2026-05-20", "10:00:00",
            "Programada", 1, 1
        );
        boolean citaInsertada = citaDao.insertar(nuevaCita);
        System.out.println("Cita insertada: " + citaInsertada);

        // ── 6. CONSULTAR TODAS las citas ────────────────────────────
        System.out.println("\n=== CONSULTAR TODAS LAS CITAS ===");
        List<Cita> listaCitas = citaDao.consultarTodos();
        listaCitas.forEach(System.out::println);

        // ── 7. ACTUALIZAR estado de cita ────────────────────────────
        System.out.println("\n=== ACTUALIZAR CITA ===");
        Cita citaAActualizar = citaDao.consultarPorId(1);
        if (citaAActualizar != null) {
            citaAActualizar.setEstado("Cancelada");
            boolean citaActualizada = citaDao.actualizar(citaAActualizar);
            System.out.println("Cita actualizada: " + citaActualizada);
        }

        // ── 8. ELIMINAR paciente de prueba ──────────────────────────
        System.out.println("\n=== ELIMINAR PACIENTE ===");
        boolean eliminacionExitosa = pacienteDao.eliminar(99);
        System.out.println("Paciente eliminado: " + eliminacionExitosa);
    }
}
