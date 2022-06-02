# When to prefer Multithreaded Architecture

- Prefer if the asks share a lot of data
- Threads are much faster to create and destroy
- Switching between threads of the same process is faster

# When to prefer Multi-Process Architecture

- Security and stability are of higher importance 
- Tasks are unrelated to each other

# Summary

- Context Switches, and their impact on performance ( thrashing )
- How thread scheduling works in the OS
- When to prefer Multithreaded over Multi-Processes architecture