# settings_local.py para Django backend_administracion

from django.core.management.utils import get_random_secret_key

SECRET_KEY = get_random_secret_key()
