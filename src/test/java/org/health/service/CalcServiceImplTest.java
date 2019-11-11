package org.health.service;

import org.health.service.impl.CalcServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CalcServiceImplTest {

    @Mock
    private CalcService calcService;

    @InjectMocks
    CalcServiceImpl calcs = new CalcServiceImpl(calcService);

    @Test
    public void testAdd() {
        when(calcs.add(20.0, 30.0)).thenReturn(50.0);

        assertEquals(calcs.add(20, 30), 50.0, 0);

        verify(calcService).add(20.0, 30.0);

        doReturn(15.0).when(calcService).add(10.0, 5.0);
        assertEquals(calcService.add(10.0, 5.0), 15.0, 0);
        verify(calcService).add(10.0, 5.0);
    }

    @Test(expected = RuntimeException.class)
    public void generateExceptionWithMockito() {
        when(calcService.divide(15.0, 3)).thenReturn(5.0);
        assertEquals(calcService.divide(15.0, 3), 5, 0);

        verify(calcService).divide(15.0, 3);

        RuntimeException exception = new RuntimeException("Division by zero");

        doThrow(exception).when(calcService).divide(15.0, 0);

        assertEquals(calcService.divide(15.0, 0), 0.0, 0);
        verify(calcService).divide(15.0, 0);
    }

    @Test
    public void testSpy(){
        CalcServiceImpl service = spy(new CalcServiceImpl(calcService));
        when(service.double15()).thenReturn(23.0);

        service.double15();

        verify(service).double15();

        assertEquals(23, service.double15(), 0);

        verify(service, atLeast(2)).double15();
    }


}
