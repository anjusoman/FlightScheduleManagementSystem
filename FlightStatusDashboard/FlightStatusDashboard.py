from flask import Flask, request, jsonify, render_template
from flask_socketio import SocketIO, emit

app = Flask(__name__)
app.config['SECRET_KEY'] = 'FAKEKEY'
socketio = SocketIO(app, cors_allowed_origins="*")

@app.route('/')
@app.route('/index')
def index():
    return render_template('index.html')  # Serve the HTML file

@socketio.on('connect')
def handle_connect():
    emit('connection', {'data': 'Connected to WebSocket!'})

@app.route('/new-flight-event', methods=['POST'])
def new_flight_event():
    # Get flight data from Lambda request
    data = request.json['detail']
    print(data)
    # Emit the new flight data to connected WebSocket clients
    socketio.emit('new_flight', data)

    return jsonify({'status': 'Flight data received and emitted'}), 200

if __name__ == '__main__':
    socketio.run(app, debug=True)
