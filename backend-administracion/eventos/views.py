from django.shortcuts import render
from rest_framework import viewsets
from .models import Evento, Departamento, Carrera, TipoEvento
from .serializers import EventoSerializer, DepartamentoSerializer, CarreraSerializer, TipoEventoSerializer
from rest_framework.permissions import IsAdminUser

class EventoViewSet(viewsets.ModelViewSet):
    queryset = Evento.objects.all()
    serializer_class = EventoSerializer
    permission_classes = [IsAdminUser]  # Solo admin accede a estas APIs

class DepartamentoViewSet(viewsets.ModelViewSet):
    queryset = Departamento.objects.all()
    serializer_class = DepartamentoSerializer
    permission_classes = [IsAdminUser]

class CarreraViewSet(viewsets.ModelViewSet):
    queryset = Carrera.objects.all()
    serializer_class = CarreraSerializer
    permission_classes = [IsAdminUser]

class TipoEventoViewSet(viewsets.ModelViewSet):
    queryset = TipoEvento.objects.all()
    serializer_class = TipoEventoSerializer
    permission_classes = [IsAdminUser]


# Create your views here.
