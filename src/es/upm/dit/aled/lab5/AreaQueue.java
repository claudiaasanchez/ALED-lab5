package es.upm.dit.aled.lab5;

import java.util.LinkedList;
import java.util.Queue;

import es.upm.dit.aled.lab5.gui.Position2D;

/**
 * Extension of Area that maintains a strict queue of the Patients waiting to
 * enter in it. After a Patient exits, the first one in the queue will be
 * allowed to enter.
 * 

 * @author rgarciacarmona
 */
public class AreaQueue extends Area {
	
	protected Queue<Patient> waitQueue;

	public AreaQueue(String name, int time, int capacity, Position2D position) {
		super(name, time, capacity, position);
		waitQueue = new LinkedList<>();
	}
	@Override
	public synchronized void enter(Patient p){
		try {
			waiting++; //por que si entra pero la lista de waiting es 0, seria un num negativo
			waitQueue.add(p); //si no puedes entrar vas a la cola pero si lo ongo aqui siempre se añade
			//si no es el primero espera
			while ((this.numPatients >= this.capacity)||(p!=waitQueue.peek())) {
				wait(); //p.wait()//solo entra quien llama a este metodo, solo el patient
			}
			numPatients++;
			waiting--;
			waitQueue.remove(p);
		}catch(InterruptedException e) {
			System.out.print("El num de pacientes es mayor que la capacidad");
		}
		
	}
	
	// TODO
	
}
