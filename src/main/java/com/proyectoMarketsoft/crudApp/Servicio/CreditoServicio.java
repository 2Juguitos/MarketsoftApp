package com.proyectoMarketsoft.crudApp.Servicio;

import com.proyectoMarketsoft.crudApp.Modelo.Administrador;
import com.proyectoMarketsoft.crudApp.Modelo.Credito;
import com.proyectoMarketsoft.crudApp.Modelo.Cliente;
import com.proyectoMarketsoft.crudApp.Modelo.RegistroCredito;
import com.proyectoMarketsoft.crudApp.Modelo.RegistroCreditoID;
import com.proyectoMarketsoft.crudApp.Repositorio.AdministradorRepositorio;
import com.proyectoMarketsoft.crudApp.Repositorio.CreditoRepositorio;
import com.proyectoMarketsoft.crudApp.Repositorio.RegistroCreditoRepositorio;
import com.proyectoMarketsoft.crudApp.Repositorio.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreditoServicio {

    @Autowired
    private CreditoRepositorio creditoRepositorio;

    @Autowired
    private RegistroCreditoRepositorio regCreditoRepositorio;

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private AdministradorRepositorio administradorRepositorio;

    @Transactional
    public Credito crearCreditoYRegistro(Credito credito) {
        // Validar y cargar el Cliente a partir del objeto que viene en crédito
        Cliente cliente = clienteRepositorio.findById(credito.getCliente().getIdCliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        credito.setCliente(cliente);

        // Persistir el crédito
        Credito creditoGuardado = creditoRepositorio.save(credito);

        // Verificar que se haya enviado el idAdministrador (campo transitorio)
        if (credito.getIdAdministrador() == null) {
            throw new RuntimeException("No se proporcionó el idAdministrador");
        }

        // Buscar el administrador
        Administrador admin = administradorRepositorio.findById(credito.getIdAdministrador())
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado"));

        // Crear el registro de relación
        RegistroCredito registro = new RegistroCredito();
        registro.setId(new RegistroCreditoID(creditoGuardado.getIdCredito(), credito.getIdAdministrador()));
        registro.setCredito(creditoGuardado);
        registro.setAdministrador(admin);

        regCreditoRepositorio.save(registro);

        return creditoGuardado;
    }
}
