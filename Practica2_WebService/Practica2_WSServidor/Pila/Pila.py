from graphviz import Digraph
from .NodoPila import NodoPila

class Pila(object):
	
	def __init__(self):
		self.inicio = None
		self.fin = None
		self.indice = 0
		
	def estaVacia(self):
		if self.inicio == None:
			return True
		else:
			return False

	def push(self, dato):
		nuevo = NodoPila(self.indice, dato)

		if self.estaVacia() == True:
			self.inicio = self.fin = nuevo
		else:
			nuevo.anterior = self.fin
			self.fin.siguiente = nuevo
			self.fin = nuevo

		self.indice += 1

	def mostrar(self):
		if self.estaVacia() == True:
			print ("Pila Vacia")
		else:
			temp = self.fin
			while temp != None:
				print (temp.getIndice() , "--" , temp.getDato())
				temp = temp.anterior

	def pop(self, dato):
		if self.estaVacia() == False:
			temp = self.fin
			if self.fin == self.inicio:
				self.inicio = self.fin = None
			else:
				self.fin = self.fin.anterior
				self.fin.siguiente = None
			self.indice = self.indice - 1
			print ("Dato " , str(temp.getDato()) , "eliminado")
			return temp.getDato()

	def graficar(self):
		dot = Digraph(comment='Pila',format='jpg',node_attr={'shape':'doublecircle'},name='Pila')
		dot.graph_attr['rankdir']='UD'
		dot  #doctest: +ELLIPSIS
		temp = self.inicio
		if temp == None:
			print ("Pila vacia")
			dot.node("1","Pila Vacia")
			dot.render('test-output/Pila', view = True)
		else:
			print (temp.getIndice())
			print (temp.getDato())
			while temp != None:				
				dot.node(str(temp.getIndice()), temp.getDato())
				if temp.siguiente != None:
					dot.node(str(temp.siguiente.getIndice()), temp.siguiente.getDato())
					dot.edge(str(temp.getIndice()), str(temp.siguiente.getIndice()), constraint='false')
				print (temp.getIndice())
				print (temp.getDato())
				temp = temp.siguiente
			print(dot.source)
			dot.render('test-output/Pila', view = True)