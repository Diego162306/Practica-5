from flask import Flask, Blueprint, render_template
import requests # type: ignore
from flask import flash, redirect
from urllib.parse import quote
from flask import jsonify
from flask import request



app = Flask(__name__)
router = Blueprint('router', __name__)

@router.route('/')
def home():
    return render_template('templateFirst.html')

@router.route('/Tablas')
def tablas():
    return render_template('tables.html')

@router.route('/Grafo')
def grafo():
    return render_template('grafo.html')

@router.route('/ResgisrarFa')
def ResgisrarFa():
    return render_template('familiaagg.html')

# @router.route('/familia/list')
# def list_person(msg=''):
#     regFA = requests.get("http://localhost:8088/api/familia/list")
#     FaData = regFA.json()
#     print(FaData)
    
#     return render_template('tables.html', listaFamilia=FaData["data"])

@router.route('/familia/list', methods=['GET'])
def list_person():
    atributo = request.args.get('atributo', 'apellido')  # Valor predeterminado
    metodo = request.args.get('metodo', 'OrderQuick')  # Valor predeterminado
    tipo = request.args.get('tipo', '0')  # Valor predeterminado

    # Si hay parámetros de ordenación, redirigir a `orderPerson`
    if atributo and metodo and tipo:
        return redirect(f"/familia/list/{atributo}/{tipo}/{metodo}")

    # Si no hay parámetros de ordenación, mostrar datos sin ordenar
    try:
        regFA = requests.get("http://localhost:8088/api/familia/list")
        FaData = regFA.json()
        print("Datos recibidos:", FaData)  # Línea de depuración
        return render_template('tables.html', listaFamilia=FaData["data"])
    except requests.RequestException as e:
        flash(f"Error de conexión: {str(e)}", category='error')
        return render_template('tables.html', listaFamilia=[])

@router.route('/familia/list/<atributo>/<tipo>/<metodo>')
def orderPerson (atributo, tipo, metodo):
    try:
        print(f"Recibido: atributo={atributo}, tipo={tipo}, metodo={metodo}")
        if metodo == "OrderQuick":
            url = f"http://localhost:8088/api/familia/list/OrderQuick/{atributo}/{tipo}"
        elif metodo == "OrderMer":
            url = f"http://localhost:8088/api/familia/list/OrderMer/{atributo}/{tipo}"
        elif metodo == "OrderShell":
            url = f"http://localhost:8088/api/familia/list/OrderShell/{atributo}/{tipo}"
        else:
            flash("Método de ordenación no válido", category='error')
            return render_template('tables.html', list=[])

        r = requests.get(url)
        print(f"Respuesta del servidor: {r.status_code}")  # Línea de depuración

        r = requests.get(url)
        if r.status_code == 200:
            data = r.json()
            print("Datos ordenados:", data)  # Verifica que los datos se reciban correctamente
            return render_template('tables.html', listaFamilia=data["data"])
        else:
            flash("Error al ordenar los datos", category='error')
            return render_template('tables.html', listaFamilia=[], message='Error al ordenar los datos')

    except requests.RequestException as e:
        flash(f"Error de conexión: {str(e)}", category='error')
        return redirect("/familia/list")
    
@router.route('/familia/search', methods=['GET'])
def redirect_search():
    criterio = request.args.get('criterio', 'apellidofamilia')  # Valor predeterminado
    texto = request.args.get('texto', '')  # Texto a buscar
    tipo = request.args.get('tipo', 'normal')  # Tipo de búsqueda

    # Redirigir a la nueva URL de búsqueda
    return redirect(f"/familia/search/{criterio}/{texto}/{tipo}")

@router.route('/familia/search/<criterio>/<texto>/<tipo>', methods=['GET'])
def view_buscar_person(criterio, texto, tipo):
    # Mapeo de criterios
    criterios_api = {
        "apellidofamilia": "Apellido",
        "telefono": "Telefono",
        "direccion": "Direccion"
    }

    # Validar parámetros
    if criterio not in criterios_api or tipo not in ['normal', 'lineal', 'binario']:
        flash("Criterio o tipo de búsqueda no válido", category='error')
        return render_template('tables.html', listaFamilia=[])

    # Construir la URL de la API
    criterio_api = criterios_api[criterio]
    base_url = "http://localhost:8088/api/familia/list/search"

    if tipo == "normal":
        api_url = f"{base_url}/{criterio_api}/{texto}"
    elif tipo == "lineal":
        api_url = f"{base_url}/lineal/{criterio_api}/{texto}"
    elif tipo == "binario":
        api_url = f"{base_url}/binario/{criterio_api}/{texto}"

    try:
        # Realizar la solicitud a la API
        response = requests.get(api_url)
        response.raise_for_status()  # Lanza excepción si el código no es 2xx
        data = response.json()

        # Depuración: imprimir la respuesta completa de la API
        print("Respuesta de la API:", data)

        return render_template('tables.html', listaFamilia=data.get("data", []))

    except requests.RequestException as e:
        flash(f"Error de conexión: {str(e)}", category='error')
        return render_template('tables.html', listaFamilia=data)



# @router.route('/familia/search', methods=['GET'])
# def view_buscar_person():
#     criterio = request.args.get('criterio', 'apellidofamilia')
#     texto = request.args.get('texto', '')

#     if not texto:
#         flash("Por favor ingrese un texto de búsqueda", category='error')
#         return redirect('/familia/list')

#     try:
#         url = f"http://localhost:8088/api/familia/list/search/{criterio}/{quote(texto)}"
#         response = requests.get(url)
#         response.raise_for_status()
#         data = response.json()
#         return render_template('tables.html', listaFamilia=data["data"])
#     except requests.RequestException as e:
#         flash(f"Error de conexión: {str(e)}", category='error')
#         return redirect('/familia/list')


app.register_blueprint(router)

# @router.route('familia/save', methods=['POST'])
# def save_person():
#     headers = {'Content-Type': 'application/json'}
#     form = request.form

#     # Crear el diccionario para la familia
#     data_familia = {
#         "direccion": form["dir"],
#         "apellido": form["ape"],
#         "telefono": form["inte"],
#         "hacompradogenerador": form["Hcmprdgnrd"] == 'true'  # Asumiendo que el valor es un string 'true' o 'false'
#     }

#     # Inicializa el diccionario para el generador si se necesita
#     if form["tieneg"] == 'true':  # Solo si tiene generador
#         data_generador = {
#             "costo": form["cost"],
#             "consumoXHora": form["conxh"],
#             "energiaGenerada": form["energen"],
#             "uso": form["uso"],
#         }
#     else:
#         # Inicializa el generador a valores predeterminados
#         data_generador = {
#             "costo": 0,  # Inicializa a 0
#             "consumoXHora": 0,  # Inicializa a 0
#             "energiaGenerada": 0,  # Inicializa a 0
#             "uso": 'ninguno',  # Inicializa a None
#         }

#     # Hacer la petición para guardar la familia
#     r_familia = requests.post("http://localhost:8086/api/familia/save", data=json.dumps(data_familia), headers=headers)

#     transaccion = Transaccion("Guardar Familia", "Se ha guardado una familia nueva.")
#     guardar_transaccion(transaccion)
    
#     requests.post("http://localhost:8086/api/generador/save", data=json.dumps(data_generador), headers=headers)

#     transaccion = Transaccion("Guardar Generador", "Se ha guardado un generador nuevo.")
#     guardar_transaccion(transaccion)

#     transaccion = Transaccion("Guardar Generador", {"id": id})
#     guardar_transaccion(transaccion)

#     if r_familia.status_code == 200:

#         flash("Registro guardado correctamente", category='info')
#         return redirect('/admin/familia/list')
#     else:
#         flash(r_familia.json().get("data", "Error al guardar la familia"), category='error')
#         return redirect('/admin/familia/list')

