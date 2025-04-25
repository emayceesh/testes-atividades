package app.service.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import app.service.CalculoService;

@SpringBootTest
public class CalculoServiceTest {
	
	@Autowired
	CalculoService calculoService;

	@Test
	@DisplayName("Cena 01 - Testando o método somar com valores válidos")
	void cenario01() {
		List<Integer> lista = new ArrayList<>();
		lista.add(3);
		lista.add(5);
		lista.add(2);
		
		int resultadoEsperado = 10;
		int resultadoObtido = this.calculoService.soma(lista);
		
		assertEquals(resultadoEsperado, resultadoObtido);
	}
	

	@Test
	@DisplayName("Cena 02 - Testando o método somar com valores inválidos")
	void cenario02() {
		List<Integer> lista = new ArrayList<>();
		lista.add(3);
		lista.add(null);
		lista.add(2);
				
		assertThrows(Exception.class,() -> {
			this.calculoService.soma(lista);
		});
		
		
	}
	
	
	
	
	@Test
	@DisplayName("Cena 03 - Testar mediana com número par de elementos")
	void cenario03() {
		List<Integer> lista = new ArrayList<>();
		lista.add(3);
		lista.add(2);
		lista.add(3);
		lista.add(4);
		
		assertEquals(3, this.calculoService.mediana(lista));
	}
	
	@Test
	@DisplayName("Cena 04 - Testar mediana com número ímpar de elementos")
	void cenario04() {
		List<Integer> lista = new ArrayList<>();
		lista.add(3);
		lista.add(2);
		lista.add(1);
		lista.add(9);
		lista.add(4);
		
		assertEquals(3, this.calculoService.mediana(lista));
	}
	
	
	@Test
	@DisplayName("Cena 05 - Testar mediana com número ímpar de elementos")
	void cenario05() {
		List<Integer> lista = new ArrayList<>();
		lista.add(8);
		lista.add(2);
		lista.add(1);
		lista.add(9);
		lista.add(39);
		lista.add(339);
		lista.add(4);
		
		assertEquals(8, this.calculoService.mediana(lista));
	}
	
	//TESTES CRIADOS PARA A ATIVIDADE
	
	@Test
	@DisplayName("Cena 06 - Testar média com valores positivos")
	void cenario06() {
		List<Integer> lista = new ArrayList<>();
		lista.add(10);
		lista.add(20);
		lista.add(30);
		
		assertEquals(20.0, this.calculoService.media(lista));
	}
	
	@Test
    @DisplayName("Cena 07 - Testar média com valor negativo incluso") // ALTERADO
    void cenario07() {
        List<Integer> lista = new ArrayList<>();
        lista.add(-10);
        lista.add(10);
        lista.add(10);

        double resultado = Math.round(this.calculoService.media(lista) * 100.0) / 100.0;
        assertTrue(resultado >= 3.32 && resultado <= 3.34);
    }
	
	//TESTES DE MAIOR NÚMERO 
	@Test
    @DisplayName("Cena 08 - Testar maior número com valor padrão") // ALTERADO
    void cenario08() {
        List<Integer> lista = new ArrayList<>();
        lista.add(1);
        lista.add(4);
        lista.add(9);
        lista.add(3);

        int maior = this.calculoService.buscarMaiorNumero(lista);
        Assertions.assertThat(maior).isEqualTo(9);
    }
	
	 @Test
	    @DisplayName("Cena 09 - Testar maior número com todos negativos")
	    void cenario09() {
	        List<Integer> lista = new ArrayList<>();
	        lista.add(-2);
	        lista.add(-10);
	        lista.add(-4);

	        int resultado = this.calculoService.buscarMaiorNumero(lista);
	        assertFalse(resultado > 0);
	    }
	
	@Test
	@DisplayName("Cena 10 - Testar maior número com lista contendo zero")
	void cenario10() {
	    List<Integer> lista = List.of(0, -1, -5, -2);
	    assertEquals(0, this.calculoService.buscarMaiorNumero(lista));
	}

	//TESTES MENOR NÚMERO 
	@Test
    @DisplayName("Cena 11 - Testar menor número com valor padrão") // ALTERADO
    void cenario11() {
        List<Integer> lista = new ArrayList<>();
        lista.add(7);
        lista.add(3);
        lista.add(9);

        int menor = this.calculoService.buscarMenorNumero(lista);
        Assertions.assertThat(menor).isLessThan(5);
    }

	@Test
	@DisplayName("Cena 12 - Testar menor número com negativos")
	void cenario12() {
		List<Integer> lista = new ArrayList<>();
		lista.add(5);
		lista.add(-7);
		lista.add(3);
		
		assertEquals(-7, this.calculoService.buscarMenorNumero(lista));
	}
	
	@Test
	@DisplayName("Cena 13 - Testar menor número com todos positivos e crescente")
	void cenario13() {
	    List<Integer> lista = List.of(1, 2, 3, 4, 5);
	    assertEquals(1, this.calculoService.buscarMenorNumero(lista));
	}

	//TESTES MODA 
	@Test
	@DisplayName("Cena 14 - Testar moda com números repetidos")
	void cenario14() {
	    List<Integer> lista = new ArrayList<>();
	    lista.add(10);
	    lista.add(20);
	    lista.add(20);
	    lista.add(30);
	    
	    assertEquals(20, this.calculoService.moda(lista));
	}
	
	@Test
	@DisplayName("Cena 15 - Testar moda com todos os números iguais")
	void cenario15() {
	    List<Integer> lista = new ArrayList<>();
	    lista.add(5);
	    lista.add(5);
	    lista.add(5);
	    
	    assertEquals(5, this.calculoService.moda(lista));
	}
	
	@Test
    @DisplayName("Cena 16 - Testar moda com vários números com mesma frequência") // ALTERADO
    void cenario16() {
        List<Integer> lista = List.of(1, 2, 3, 4); // Todos aparecem 1 vez
        double moda = this.calculoService.moda(lista);
        Assertions.assertThat(moda).isIn(1.0, 2.0, 3.0, 4.0);
    }

	//TESTE PRODUTOS (MULTIPLICAÇÃO)
	@Test
	@DisplayName("Cena 17 - Testar produto")
	void cenario17() {
	    List<Integer> lista = new ArrayList<>();
	    lista.add(1);
	    lista.add(2);
	    lista.add(3);
	    
	    assertEquals(6, this.calculoService.produto(lista));
	}
	
	@Test
    @DisplayName("Cena 18 - Testar produto com número zero") // ALTERADO
    void cenario18() {
        List<Integer> lista = List.of(2, 0, 4);
        int produto = this.calculoService.produto(lista);
        assertThat(produto).isEqualTo(0); // usando AssertJ
    }

	@Test
	@DisplayName("Cena 19 - Testar produto com números negativos")
	void cenario19() {
	    List<Integer> lista = List.of(-2, 3);
	    assertEquals(-6, this.calculoService.produto(lista));
	}

	
	@Test
	@DisplayName("Cena 20 - Testar desvio padrão com valores distintos")
	void cenario20() {
	    List<Integer> lista = List.of(2, 4, 4, 4, 5, 5, 7, 9);
	    double resultado = Math.round(this.calculoService.desvioPadrao(lista) * 100.0) / 100.0;
	    assertEquals(2.0, resultado);
	}

	@Test
    @DisplayName("Cena 21 - Testar desvio padrão com todos iguais") // ALTERADO
    void cenario21() {
        List<Integer> lista = List.of(10, 10, 10, 10);
        double resultado = this.calculoService.desvioPadrao(lista);
        assertTrue(resultado == 0.0);
    }

	@Test
	@DisplayName("Cena 22 - Testar desvio padrão com valores negativos")
	void cenario22() {
	    List<Integer> lista = List.of(-2, -4, -4, -4, -5, -5, -7, -9);
	    double resultado = Math.round(this.calculoService.desvioPadrao(lista) * 100.0) / 100.0;
	    assertEquals(2.0, resultado);
	}

	
	

}
