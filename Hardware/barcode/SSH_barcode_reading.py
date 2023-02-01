import paramiko

# Connect to the remote server
ssh = paramiko.SSHClient()
ssh.set_missing_host_key_policy(paramiko.AutoAddPolicy())
ssh.connect("192.168.40.111", username="ssafy", password="1234")

# Start a shell on the remote server
channel = ssh.invoke_shell()

# Read the keyboard input from the remote server
input_value = channel.recv(1024).decode("utf-8")

# Send the keyboard input back to the client machine
print(input_value)

# Close the SSH connection
ssh.close()