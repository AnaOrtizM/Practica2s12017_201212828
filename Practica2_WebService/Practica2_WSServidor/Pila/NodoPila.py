class NodoPila(object):
	def __init__ (self, indice, dato):
		self.indice = indice
		self.dato = dato
		self.siguiente = None
		self.anterior = None

	def getIndice(self):
		return self.indice

	def getDato(self):
		return self.dato
