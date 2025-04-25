package app.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.dto.Entrada;
import app.entity.Calculo;
import app.repository.CalculoRepository;

@Service
public class CalculoService {

	@Autowired
	private CalculoRepository calculoRepository;

	public Calculo calcular(Entrada entrada) {

		Calculo calculo = new Calculo();
		calculo.setLista(entrada.getLista());
		calculo.setSoma(this.soma(entrada.getLista()));
		calculo.setMedia(this.media(entrada.getLista()));
		calculo.setMediana(this.mediana(entrada.getLista()));
		
		calculo = this.calculoRepository.save(calculo);

		return calculo;

	}

	public int soma(List<Integer> lista) {
		int soma = 0;
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i) == null)
				throw new RuntimeException("dslçfjakd");
			else
				soma += lista.get(i);
		}
		return soma;
	}

	public double media(List<Integer> lista) {
		int soma = this.soma(lista);
	    return soma / (double) lista.size();
	}

	public double mediana(List<Integer> lista) {
		if (lista == null || lista.isEmpty()) {
			throw new IllegalArgumentException("A lista não pode ser nula ou vazia");
		}

		Collections.sort(lista);

		if (lista.size() % 2 == 1) { // ÍMPAR
			return lista.get(lista.size() / 2);
		} else {
			int meio1 = lista.get(lista.size() / 2 - 1);
			int meio2 = lista.get(lista.size() / 2);
			return (meio1 + meio2) / 2;
		}
	}

	public int buscarMaiorNumero(List<Integer> lista) {

		int maiorNumero = lista.get(0);

		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i) > maiorNumero)
				maiorNumero = lista.get(i);
		}

		return maiorNumero;

	}

	public int buscarMenorNumero(List<Integer> lista) {

		int menorNumero = lista.get(0);

		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i) < menorNumero)
				menorNumero = lista.get(i);
		}

		return menorNumero;

	}
	
	public int produto(List<Integer> lista) {
        int produto = 1;
        for (Integer numero : lista) {
            produto *= numero;
        }
        return produto;
    }
	
	public double moda(List<Integer> lista) {
        Map<Integer, Integer> frequencia = new HashMap<>();
        for (Integer numero : lista) {
            frequencia.put(numero, frequencia.getOrDefault(numero, 0) + 1);
        }
        
        int moda = lista.get(0);
        int maxCount = 0;
        
        for (Map.Entry<Integer, Integer> entry : frequencia.entrySet()) {
            if (entry.getValue() > maxCount) {
                moda = entry.getKey();
                maxCount = entry.getValue();
            }
        }
        
        return moda;
    }
	
	public double desvioPadrao(List<Integer> lista) {
	    double media = media(lista);
	    double somaDosQuadrados = 0.0;

	    for (int valor : lista) {
	        somaDosQuadrados += Math.pow(valor - media, 2);
	    }

	    return Math.sqrt(somaDosQuadrados / lista.size());
	}


}
