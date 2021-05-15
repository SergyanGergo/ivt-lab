package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {
  private TorpedoStore mockDA1;
  private TorpedoStore mockDA2;
  private GT4500 ship;

  @BeforeEach
  public void init(){
    mockDA1 = mock(TorpedoStore.class);
    mockDA2 = mock(TorpedoStore.class);
    this.ship = new GT4500(mockDA1, mockDA2);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(mockDA1.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockDA1, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(mockDA1.fire(1)).thenReturn(true);
    when(mockDA2.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(mockDA1, times(1)).fire(1);
    verify(mockDA2, times(1)).fire(1);
  }
  @Test
  public void fire2Torpedos_Single_Success(){
    // Arrange
    when(mockDA1.fire(1)).thenReturn(true);
    when(mockDA2.fire(1)).thenReturn(true);
    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockDA1, times(1)).fire(1);
    verify(mockDA2, times(1)).fire(1);
  }
  @Test
  public void fireTorpedo_Single_Primary_isEmpty(){
    // Arrange
    when(mockDA1.isEmpty()).thenReturn(true);
    when(mockDA1.fire(1)).thenReturn(false);
    when(mockDA2.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockDA1, times(0)).fire(1);
    verify(mockDA2, times(1)).fire(1);
  }
  @Test
  public void fireTorpedos_Single_Fail(){
    // Arrange
    when(mockDA1.fire(1)).thenReturn(false);
    when(mockDA2.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
    verify(mockDA1, times(1)).fire(1);
  }
  @Test
  public void fireTorpedos_Single_Secondary_isEmpty(){
    // Arrange
    when(mockDA1.fire(1)).thenReturn(true);
    when(mockDA2.fire(1)).thenReturn(false);
    when(mockDA2.isEmpty()).thenReturn(true);
    // Act
    ship.fireTorpedo(FiringMode.SINGLE);
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockDA1, times(2)).fire(1);
    verify(mockDA2, times(0)).fire(1);
  }

}
