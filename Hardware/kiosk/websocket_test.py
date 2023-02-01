import asyncio
import websockets

async def handle_client(websocket, path):
    while True:
        message = await websocket.recv()
        response = "received: " + message
        await websocket.send(response)

start_server = websockets.serve(handle_client, "localhost", 8080)

asyncio.get_event_loop().run_until_complete(start_server)
asyncio.get_event_loop().run_forever()
