class NodoLS(object):
	def __init__ (self, indice, palabra):
		self.indice = indice
		self.palabra = palabra
		self.siguiente = None

	def getIndice(self):
		return self.indice

	def getPalabra(self):
		return self.palabra
