package com.pedido.business.generic;

import java.util.List;

public abstract class AggregateRoot<I extends Identity> extends Entity<I> {

    private final ChangeEventSubscriber changeEventSubscriber;

    public AggregateRoot(I id) {
        super(id);
        this.changeEventSubscriber = new ChangeEventSubscriber();
    }

    protected ChangeEventSubscriber.ChangeApply appendChange(DomainEvent event) {
        String nameClass = identity().getClass().getSimpleName();
        String aggregate = nameClass.replaceAll("(Identity|id|ID|Id)", "").toLowerCase();
        event.setAggregateName(aggregate);
        event.setAggregateRootId(identity().value());
        return changeEventSubscriber.appendChange(event);
    }

    public List<DomainEvent> getUncommittedChanges(){
        return List.copyOf(changeEventSubscriber.changes());
    }

    protected final void subscribe(EventChange eventChange) {
        changeEventSubscriber.subscribe(eventChange); }

    public void markChangesAsCommitted() { changeEventSubscriber.changes().clear(); }

    protected void applyEvent(DomainEvent domainEvent) {
        changeEventSubscriber.applyEvent(domainEvent); }

}
