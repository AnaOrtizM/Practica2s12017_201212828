from graphviz import Digraph
from .NodoLS import NodoLS

class ListaSimple(object):
	
	def __init__(self):
		self.inicio = None
		self.fin = None
		self.indice = 0
		
	def estaVacia(self):
		if self.inicio == None:
			return True
		else:
			return False

	def insertarInicio(self, palabra):
		nuevo = NodoLS(self.indice, palabra)
		
		if self.estaVacia() == True:
			self.inicio = self.fin = nuevo
		else:
			nuevo.siguiente = self.inicio
			self.inicio = nuevo

		self.indice += 1

	def insertarFinal(self, palabra):
		nuevo = NodoLS(self.indice, palabra)

		if self.estaVacia() == True:
			self.inicio = self.fin = nuevo
		else:
			self.fin.siguiente = nuevo
			self.fin = nuevo

		self.indice += 1

	def buscarPalabra(self, palabra):
		temp = self.inicio
		encontrado = False

		while temp != None and encontrado != True:
			if palabra == temp.getPalabra():
				encontrado = True
				return "Palabra " + temp.getPalabra() + " encontrada en el indice " + str(temp.getIndice())
			else:
				temp = temp.siguiente
		return "Palabra No Encontrada!"

	def mostrar(self):
		if self.estaVacia() == True:
			print ("Lista Vacia")
		else:
			temp = self.inicio
			while temp != None:
				print (temp.getIndice() , "--" , temp.getPalabra())
				temp = temp.siguiente				

	def eliminarIndice(self, indice):
		if self.inicio.getIndice() == indice:
			self.inicio = self.inicio.siguiente
			self.indice = self.indice - 1
			if self.fin.getIndice() == indice:
				self.fin = None
		else:
			temp = self.inicio
			while temp != None:
				if temp.siguiente != None:
					if temp.siguiente.getIndice() == indice:
						temp.siguiente = temp.siguiente.siguiente
						if self.fin.getIndice() == indice:
							self.fin = temp
						self.indice = self.indice - 1
				temp = temp.siguiente				

	def graficar(self):
		dot = Digraph(comment='Lista Simple',format='jpg',node_attr={'shape':'box'},name='Lista Simple')
		dot.graph_attr['rankdir']='UD'
		dot  #doctest: +ELLIPSIS
		temp = self.inicio
		if temp == None:
			print ("Lista vacia")
			dot.node("1","Lista Vacia")
			dot.render('test-output/ListaSimple', view = True)
		else:
			print (temp.getIndice())
			print (temp.getPalabra())
			while temp != None:				
				dot.node(str(temp.getIndice()), temp.getPalabra())
				if temp.siguiente != None:
					dot.node(str(temp.siguiente.getIndice()), temp.siguiente.getPalabra())
					dot.edge(str(temp.getIndice()), str(temp.siguiente.getIndice()), constraint='false')
				print (temp.getIndice())
				print (temp.getPalabra())
				temp = temp.siguiente
			print(dot.source)
			dot.render('test-output/ListaSimple', view = True)