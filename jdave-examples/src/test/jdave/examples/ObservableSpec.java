/*
 * Copyright 2006 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jdave.examples;

import jdave.Context;
import jdave.Specification;
import jdave.examples.observer.Observable;
import jdave.examples.observer.Observer;
import jdave.mock.Mock;

/**
 * @author Joni Freeman
 */
public class ObservableSpec extends Specification<Observable> {
    @Context
    public class ObservableHavingObserver {
        private Observable observable;
        private Mock<Observer> observer;
        
        public Observable create() {
            observable = new Observable();
            observer = mock(Observer.class);
            observable.register(observer.proxy());
            return observable;
        }
        
        public void callsOnActionWhenSignificantEventHappens() {
            observer.expects(once()).method("onAction").with(eq(observable));
            observable.significantEvent();
        }
    }
}
