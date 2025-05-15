package com.gersimuca.erp.feature.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MockitoTest {
  @Mock List<String> mockList;

  @Spy List<String> spyList = new ArrayList<>();

  @Captor ArgumentCaptor<String> captor;

  @Test
  void mocke_list_return_correct_element() {
    when(mockList.get(0)).thenReturn("Hello, Mockito!");
    assertEquals("Hello, Mockito!", mockList.get(0));
  }

  @Test
  void spy_list_return_element_to_it_correcly() {
    spyList.add("Subcribe");
    verify(spyList).add("Subcribe");
    assertEquals(1, spyList.size());
  }

  @Test
  void spy_list_return_element_to_it() {
    spyList.add("Geeking");
    verify(spyList).add(captor.capture());
    assertEquals("Geeking", captor.getValue());
  }
}
