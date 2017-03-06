from graphviz import Source
from .ListaIndice import ListaIndice
from .NodoIndice import NodoIndice
from .NodoListaNodos import NodoListaNodos

class Matriz(object):
	def __init__(self):
		self.ejeX = ListaIndice()
		self.ejeY = ListaIndice()
		self.indice = None

	def getNodoIndiceX(self, posX):
		nodoIndiceX = self.ejeX.buscar(posX)
		if nodoIndiceX == None:
			nodoIndiceX = NodoIndice(posX)
			self.ejeX.insertarIndice(nodoIndiceX)
		return nodoIndiceX

	def getNodoIndiceY(self, posY):
		nodoIndiceY = self.ejeY.buscar(posY)
		if nodoIndiceY == None:
			nodoIndiceY = NodoIndice(posY)
			self.ejeY.insertarIndice(nodoIndiceY)
		return nodoIndiceY

	def insertar(self, correo):
		nombre,posX = correo.split('@')
		posY = nombre[:1]
		nodoIndiceX = self.getNodoIndiceX(posX)
		nodoIndiceY = self.getNodoIndiceY(posY)
		nodo = self.buscar(posX, posY)
		if nodo == None:
			nodo = NodoListaNodos(correo)
			nodo.padreX = nodoIndiceX
			nodo.padreY = nodoIndiceY
			nodoIndiceX.listaNodos.insertarX(nodo)
			nodoIndiceY.listaNodos.insertarY(nodo)
			print("sdfsad")
		else:
			print("dfas")
			nodo.agregarNombre(correo)

	def buscar(self, x, y):
		tempY = self.ejeY.inicio
		while tempY != None:
			tempXinterno = tempY.listaNodos.inicio
			if tempY.getIndice() == y:
				while tempXinterno != None:
					if tempXinterno.padreX.getIndice() == x:
						return tempXinterno
					tempXinterno = tempXinterno.derecha
			tempY = tempY.siguiente
		return None

	def graficar(self):
		grafo = "digraph G {\n" + "rankdir = TB;\n" + "rank = min;\n" + "node[style=filled,shape=box, label=\"Inicio\", rankdir=UD];\n"

		tempX = self.ejeX.inicio
		tempXinterno = None
		tempY = self.ejeY.inicio

		j = 0;
		i = 0;

		while tempY != None:
			if tempY == self.ejeY.inicio:
				grafo += "\"" + str(i) + "," + str(j) + "\"[label=\"raiz\", style=filled];\n"
				i += 1
				while tempX != None:
					grafo += "\"" + str(i) + "," + str(j) + "\"[label=\""+tempX.getIndice()+"\", style=filled];\n"
					i += 1
					tempX = tempX.siguiente
				i = 0
				j += 1
			tempXinterno = tempY.listaNodos.inicio
			tempX = self.ejeX.inicio
			grafo += "\"" + str(i) + "," + str(j) + "\"[label=\""+tempY.getIndice()+"\", style=filled];\n"
			i += 1
			while tempX != None:
				if tempXinterno != None:
					if tempXinterno.padreX == tempX:
						grafo += "\"" + str(i) + "," + str(j) + "\"[label=\""+tempXinterno.getNombre()+"\", style=filled];\n"
						tempXinterno = tempXinterno.derecha
					else:
						grafo += "\"" + str(i) + "," + str(j) + "\"[label=\"no existe\", style=filled];\n"
				else:
					grafo += "\"" + str(i) + "," + str(j) + "\"[label=\"no existe\", style=filled];\n"
				i += 1
				tempX = tempX.siguiente
			i = 0
			j += 1
			tempY = tempY.siguiente
		print(str(i))
		print(str(j))
		tempX = self.ejeX.inicio
		while tempX != None:
			i += 1
			tempX = tempX.siguiente
		print(str(i))
		print(str(j))
		i += 1
		for y in range(0,j):
		    for x in range (0,i-1):
		        grafo += "\"" + str(x) + "," + str(y) + "\" -> \"" + str(x + 1) + "," + str(y) + "\"[constraint=false];\n"
		        grafo += "\"" + str(x + 1) + "," + str(y) + "\" -> \"" + str(x) + "," + str(y) + "\"[constraint=false];\n"
		        grafo += "{rank=same;\"" + str(x) + "," + str(y) + "\" \"" + str(x + 1) + "," + str(y) + "\"}\n"
		        grafo += "{rank=same;\"" + str(x + 1) + "," + str(y) + "\" \"" + str(x) + "," + str(y) + "\"}\n"

		for y in range(0,j-1):
		    for x in range (0,i):
		        grafo += "\"" + str(x) + "," + str(y) + "\" -> \"" + str(x) + "," + str(y + 1) + "\"[rankdir=UD];\n";
		        grafo += "\"" + str(x) + "," + str(y + 1) + "\" -> \"" + str(x) + "," + str(y) + "\"[rankdir=UD];\n";

		grafo += "labelloc=\"t\"; label=\" MATRIZ DISPERSA\";}"
		print(grafo)
		src = Source(grafo)
		src.render('test-output/MatrizDispersa', view = True)