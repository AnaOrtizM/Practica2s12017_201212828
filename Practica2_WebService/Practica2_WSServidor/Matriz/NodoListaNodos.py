

class NodoListaNodos(object):
	def __init__ (self, listaNombre):
		self.arriba = None
		self.abajo = None
		self.izquierda = None
		self.derecha = None
		from .NodoIndice import NodoIndice
		self.padreX = None
		self.padreY = None
		self.listaNombre = listaNombre


	def getPosX(self):
		return self.padreX.getIndice()

	def getPosY(self):
		return self.padreY.getIndice()

	def agregarNombre(self, nombre):
		self.listaNombre += '--' + nombre

	def getNombre(self):
		return self.listaNombre