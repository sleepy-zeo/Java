## Pool
链表结构的数据池，当一个数据不用的时候，不会立即析构，而是放到链表池当中，只有池子满了的时候，继续来的数据才会被析构

## Lifecycle
Lifecycle是观察者模式的一种实现方式

#### Lifecycle

```text
            --- addLifecycleListener
            |
            --- removeLifecycleListener
            |
            --- getLifecycleListeners
Lifecycle --|
            --- fireLifecycleEvent
            |
            --- start(init - start)
            |
            --- stop(stop - destroy)
```

#### LifecycleEvent

```
                  --- type
LifecycleEvent -- |
                  --- data
```

#### Circuit

```
                    fireLifecycleEvents
start or stop -------------------------------> lifecycleListeners

```