/*
 * Copyright (c) 2018-2020 DJI
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package dji.ux.beta.core.widget.simulator

import dji.keysdk.FlightControllerKey
import io.reactivex.rxjava3.core.Flowable
import dji.ux.beta.core.base.DJISDKModel
import dji.ux.beta.core.base.WidgetModel
import dji.ux.beta.core.communication.ObservableInMemoryKeyedStore
import dji.ux.beta.core.util.DataProcessor

/**
 * Simulator Indicator Widget Model
 *
 *
 * Widget Model for the [SimulatorIndicatorWidget] used to define the
 * underlying logic and communication
 */
class SimulatorIndicatorWidgetModel(
        djiSdkModel: DJISDKModel,
        keyedStore: ObservableInMemoryKeyedStore
) : WidgetModel(djiSdkModel, keyedStore) {

    //region Fields
    private val simulatorActiveDataProcessor: DataProcessor<Boolean> = DataProcessor.create(false)

    //endregion

    //region Data

    /**
     * Check if the simulator is running
     */
    val isSimulatorActive: Flowable<Boolean>
        get() = simulatorActiveDataProcessor.toFlowable()

    override fun inSetup() {
        val simulatorActiveKey = FlightControllerKey.create(FlightControllerKey.IS_SIMULATOR_ACTIVE)
        bindDataProcessor(simulatorActiveKey, simulatorActiveDataProcessor)
    }

    override fun inCleanup() {
        // No Clean up required
    }

    override fun updateStates() {
        // No States
    }
    //endregion
}
