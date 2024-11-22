import requests
import json

def obtener_informacion_articulo(item_id):
    """Obtiene detalles de un artículo específico por su ID."""
    url = f"https://api.wallapop.com/api/v3/items/{item_id}"
    response = requests.get(url, headers=HEADERS)
    if response.status_code == 200:
        return response.json()
    else:
        print(f"Error al obtener información del artículo: {response.status_code}")
        return None

def obtener_informacion_usuario(user_id):
    url = f"https://api.wallapop.com/api/v3/users/{user_id}"
    headers = {
        "User-Agent": "Mozilla/5.0",
        "Accept": "application/json"
    }
    response = requests.get(url, headers=headers)
    if response.status_code == 200:
        return response.json()
    else:
        print(f"Error al obtener información del usuario: {response.status_code}")
        return None

def listar_productos_usuario(user_id):
    url = f"https://api.wallapop.com/api/v3/users/{user_id}/items"
    headers = {
        "User-Agent": "Mozilla/5.0",
        "Accept": "application/json"
    }
    response = requests.get(url, headers=headers)
    if response.status_code == 200:
        return response.json()
    else:
        print(f"Error al obtener productos del usuario: {response.status_code}")
        return None

if __name__ == "__main__":
    user_id = "p61vodd2og65"  # Reemplaza con el ID de usuario correspondiente
    info_usuario = obtener_informacion_usuario(user_id)
    if info_usuario:
        print("Información del usuario:")
        print(json.dumps(info_usuario, indent=4))

    productos = listar_productos_usuario(user_id)
    if productos:
        print("\nProductos del usuario:")
        for producto in productos:
            print(f"- {producto['title']}: {producto['price']}€")
            

    productos = obtener_informacion_articulo("36ekr88gdn6d")
    if productos:
        print("\nitems:")
        for producto in productos:
            print(f"- {producto['title']}: {producto['price']}€")