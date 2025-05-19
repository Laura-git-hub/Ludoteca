package com.ccsw.tutorial.client;

import com.ccsw.tutorial.client.model.Client;
import com.ccsw.tutorial.client.model.ClientDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    // Test para probar que devuelve los clientes correctamente
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

    //Test para guardar un cliente nuevo.
    @Test
    public void saveNewClientShouldCreate() {
        ClientDto clientDto = new ClientDto();
        clientDto.setName("Silvia Sanz");

        when(clientRepository.findByName("Silvia Sanz")).thenReturn(Optional.empty());

        clientService.save(null, clientDto);

        verify(clientRepository).save(any(Client.class));
        System.out.println("NOMBRE DEL CLIENTE GUARDADO: " + clientDto.getName());
    }

    // Test que pruebe una modificación existente.
    public static final Long EXISTS_CLIENT_ID = 1L;
    public static final String CLIENT_NAME = "Rachel Loren";

    @Test
    public void saveExistsClientIdShouldUpdate() {
        ClientDto clientDto = new ClientDto();
        clientDto.setName(CLIENT_NAME);

        Client client = mock(Client.class);
        when(clientRepository.findById(EXISTS_CLIENT_ID)).thenReturn(Optional.of(client));
        when(clientRepository.findByName(CLIENT_NAME)).thenReturn(Optional.empty());

        clientService.save(EXISTS_CLIENT_ID, clientDto);

        verify(clientRepository).save(client);

        System.out.println("Test ejecutado correctamente");
        System.out.println("Se intentó actualizar el cliente con ID: " + EXISTS_CLIENT_ID);
        System.out.println("NOMBRE DEL CLIENTE: " + CLIENT_NAME);
    }

    // Test para comprobar si se crea un Client SIN PERMITIR DUPLICADOS
    @Test
    public void saveWithDuplicateNameShouldThrowException() {
        ClientDto clientDto = new ClientDto();
        clientDto.setName(CLIENT_NAME);

        Client existingClient = new Client();
        existingClient.setId(2L); // Simula que ya existe otro cliente con ese nombre

        when(clientRepository.findByName(CLIENT_NAME)).thenReturn(Optional.of(existingClient));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            clientService.save(null, clientDto);
        });

        assertEquals("Ya existe un cliente con ese nombre.", exception.getMessage());
        System.out.println("EXCEPCIÓN CAPTURADA: " + exception.getMessage());
    }

    // Test de borrado
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
