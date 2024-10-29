import json

def lambda_handler(event, context):
    import requests

    # Forward the event to the Flight Status Dashboard
    response = requests.post("http://flight-status-dashboard:5000/new-flight-event", json=event)

    return {
        'statusCode': response.status_code,
        'body': json.dumps(response.json())
    }
