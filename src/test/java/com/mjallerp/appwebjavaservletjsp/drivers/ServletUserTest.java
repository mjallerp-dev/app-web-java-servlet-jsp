package com.mjallerp.appwebjavaservletjsp.drivers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.PrintWriter;

import static org.mockito.ArgumentMatchers.startsWith;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServletUserTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private PrintWriter writer;

    private ServletUser servlet;

    @BeforeEach
    void setUp() throws Exception {
        servlet = new ServletUser();
        when(response.getWriter()).thenReturn(writer);
    }

    @Test
    void testSalirInvalidaSesionYRedirige() throws Exception {
        when(request.getParameter("accion")).thenReturn("salir");
        when(request.getSession()).thenReturn(session);

        servlet.processRequest(request, response);

        verify(session).setAttribute("user.login", null);
        verify(session).invalidate();
        verify(response).sendRedirect("index.jsp?mensaje=Sesion Cerrada");
    }

    @Test
    void testAccionInvalidaRedirigeAMensaje() throws Exception {
        when(request.getParameter("accion")).thenReturn("accionInexistente");

        servlet.processRequest(request, response);

        verify(response).sendRedirect("web/users/mensaje.jsp?mensaje=La Accion Solicitada no es Correcta");
    }

    @Test
    void testAccionNulaRedirigeAMensaje() throws Exception {
        servlet.processRequest(request, response);

        verify(response).sendRedirect(startsWith("web/users/mensaje.jsp?mensaje="));
    }
}
