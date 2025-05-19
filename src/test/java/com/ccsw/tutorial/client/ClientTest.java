package com.ccsw.tutorial.client;

import com.ccsw.tutorial.client.model.Client;
import com.ccsw.tutorial.client.model.ClientDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    //Test para probar que devuelve los clientes correctamente
    @Test
    public void findAllShouldReturnAllClients() {
        List<Client> listClient = new ArrayList<>();
        listClient.add(mock(Client.class));

        when(clientRepository.findAll()).thenReturn(listClient);

        List<Client> clients = clientService.findAll();

        assertNotNull(clients);
        assertEquals(1, clients.size());

        // Mostrar los clientes en consola
        System.out.println("CLIENTES ENCONTRADOS: " + clients);

    }

    //Test para comprobar si se crea un Client
    public static final String CLIENT_NAME = "Rachel Loren";

    @Test
    public void saveNotExistsClientIdShouldInsert() {

        ClientDto clientDto = new ClientDto();
        clientDto.setName(CLIENT_NAME);

        ArgumentCaptor<Client> client = ArgumentCaptor.forClass(Client.class);

        clientService.save(null, clientDto);

        verify(clientRepository).save(client.capture());

        // Mostrar el nombre del cliente guardado
        System.out.println("CLIENTE GUARDADO: " + client.getValue().getName());
        assertEquals(CLIENT_NAME, client.getValue().getName());
    }

    //Test que pruebe una modificación existente.
    public static final Long EXISTS_CLIENT_ID = 1L;

    @Test
    public void saveExistsClientIdShouldUpdate() {

        ClientDto clientDto = new ClientDto();
        clientDto.setName(CLIENT_NAME);

        Client client = mock(Client.class);
        when(clientRepository.findById(EXISTS_CLIENT_ID)).thenReturn(Optional.of(client));

        clientService.save(EXISTS_CLIENT_ID, clientDto);

        verify(clientRepository).save(client);

        System.out.println("Test ejecutado correctamente");
        System.out.println("Se intentó actualizar el cliente con ID: " + EXISTS_CLIENT_ID);
        System.out.println("NOMBRE DEL CLIENTE: " + CLIENT_NAME);
    }

    //Test de borrado
    @Test
    public void deleteExistsClientIdShouldDelete() throws Exception {

        Client client = mock(Client.class);
        when(clientRepository.findById(EXISTS_CLIENT_ID)).thenReturn(Optional.of(client));

        clientService.delete(EXISTS_CLIENT_ID);

        verify(clientRepository).deleteById(EXISTS_CLIENT_ID);
        System.out.println("TEST deleteExistsClientIdShouldDelete EJECUTADO CORRECTAMENTE");
        System.out.println("CLIENTE ELIMINADO CON ID: " + EXISTS_CLIENT_ID);
    }
}


