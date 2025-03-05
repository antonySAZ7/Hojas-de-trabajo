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

def generar_graficas():
    procesos = [25, 50, 100, 150, 200]
    configuraciones = [
        {"ram": 100, "cpu": 1, "instrucciones": 3},
        {"ram": 200, "cpu": 1, "instrucciones": 3},
        {"ram": 100, "cpu": 1, "instrucciones": 6},
        {"ram": 100, "cpu": 2, "instrucciones": 3},
    ]
    
    plt.figure(figsize=(10, 6))
    for conf in configuraciones:
        tiempos_prom = []
        desv_std = []
        for proc in procesos:
            tiempos = []
            env = simpy.Environment()
            ram = simpy.Container(env, init=conf["ram"], capacity=conf["ram"])
            cpu = simpy.Resource(env, capacity=conf["cpu"])
            colaEspera = []
            env.process(generarProcesos(env, proc, ram, cpu, 10, conf["instrucciones"], tiempos, colaEspera))
            env.run()
            tiempos_prom.append(np.mean(tiempos))
            desv_std.append(np.std(tiempos))
        plt.errorbar(procesos, tiempos_prom, yerr=desv_std, label=f"RAM {conf['ram']}, CPU {conf['cpu']}, Inst {conf['instrucciones']}", capsize=5, marker='o', linestyle='-')
    
    plt.xlabel("Número de procesos", fontsize=12)
    plt.ylabel("Tiempo promedio de ejecución", fontsize=12)
    plt.legend()
    plt.title("Tiempo de ejecución en diferentes configuraciones", fontsize=14, fontweight='bold')
    plt.grid(True, linestyle='--', linewidth=0.5)
    plt.xticks(procesos)
    plt.yticks(fontsize=10)
    plt.show()

generar_graficas()