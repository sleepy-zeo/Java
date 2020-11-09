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