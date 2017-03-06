from graphviz import Digraph
from .NodoCola import NodoCola

class Cola(object):
	
	def __init__(self):
		self.inicio = None
		self.fin = None
		self.indice = 0
		
	def estaVacia(self):
		if self.inicio == None:
			return True
		else:
			return False

	def queue(self, dato):
		nuevo = NodoCola(self.indice, dato)

		if self.estaVacia() == True:
			self.inicio = self.fin = nuevo
		else:
			self.fin.siguiente = nuevo
			self.fin = nuevo

		self.indice += 1

	def mostrar(self):
		if self.estaVacia() == True:
			print ("Cola Vacia")
		else:
			temp = self.inicio
			while temp != None:
				print (temp.getIndice() , "--" , temp.getDato())
				temp = temp.siguiente

	def dequeue(self, dato):
		if self.estaVacia() == False:
			temp = self.inicio
			self.inicio = self.inicio.siguiente
			if self.estaVacia() == True:
				self.fin = None
			self.indice = self.indice - 1
			print ("Dato " , str(temp.getDato()) , "eliminado")
		return temp.getDato()

	def graficar(self):
		dot = Digraph(comment='Cola',format='jpg',node_attr={'shape':'circle'},name='Cola')
		dot.graph_attr['rankdir']='UD'
		dot  #doctest: +ELLIPSIS
		temp = self.inicio
		if temp == None:
			print ("Cola vacia")
			dot.node("1","Cola Vacia")
			dot.render('test-output/Cola', view = True)
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
			dot.render('test-output/Cola', view = True)