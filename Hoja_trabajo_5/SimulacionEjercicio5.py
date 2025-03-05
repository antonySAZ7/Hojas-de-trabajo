import simpy
import random
import numpy as np
import matplotlib.pyplot as plt

def solicitarOpcion(mensaje, opcionesUnicas):
    while True:
        try:
            valor = int(input(mensaje))
            if valor in opcionesUnicas:
                return valor
            else:
                print(f"error!!!, solo se pueden estas opciones :) : {opcionesUnicas}")
        except ValueError:
            print("Entrada no valida, debe ser un numero entero.") 
            
def proceso(instancia, nombre, ram, cpu, instruccionesCiclo, tiempos, colaEspera):
    llegada = instancia.now  
    memoriaRequerida = random.randint(1, 10) 
    instrucciones = random.randint(1, 10) 
    
    print(f"En este momento {instancia.now:.4f} el proceso {nombre}: Llega y solicita de ram esto: {memoriaRequerida}")
    yield ram.get(memoriaRequerida)  
    print(f"en este segundo {instancia.now:.4f} al proceso {nombre}: se le asigno ram")
    
    while instrucciones > 0:
        with cpu.request() as requerimiento:
            yield requerimiento  
            print(f" en este momento {instancia.now:.4f} el proceso {nombre} se esta ejecutando, instrucciones restantes: {instrucciones}")
            yield instancia.timeout(1)  
            instrucciones -= instruccionesCiclo
        
        opcion1 = random.randint(1, 21)
        if opcion1 == 1:
            colaEspera.append(instancia.now)
            print(f"en este momomento {instancia.now:.4f} el proceso {nombre} entra a espera de I/O")
            yield instancia.timeout(random.uniform(1, 5))
            print(f"en este momento {instancia.now:.4f} el proceso {nombre} regresa de espera de I/O")
        elif opcion1 == 2:
            print(f"en este momento {instancia.now:.4f} el proceso {nombre} esta de regreso  a Ready")
            continue
    
    yield ram.put(memoriaRequerida)
    print(f"En este justo momento {instancia.now:.4f} este proceso {nombre} termina y libera esto de memorio: {memoriaRequerida}")
    tiempos.append(instancia.now - llegada)
    
def generarProcesos(instancia, cantProcesos, ram, cpu, intervalo, instruccionesCiclo, tiempos, colaEspera):
    for i in range(cantProcesos):
        instancia.process(proceso(instancia, f"Proceso-{i}", ram, cpu, instruccionesCiclo, tiempos, colaEspera))
        yield instancia.timeout(random.expovariate(1.0 / intervalo))
