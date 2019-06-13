/*
 * Copyright (c) 2015 SONATA-NFV, 2019 5GTANGO [, ANY ADDITIONAL AFFILIATION]
 * ALL RIGHTS RESERVED.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Neither the name of the SONATA-NFV, 5GTANGO [, ANY ADDITIONAL AFFILIATION]
 * nor the names of its contributors may be used to endorse or promote
 * products derived from this software without specific prior written
 * permission.
 *
 * This work has been performed in the framework of the SONATA project,
 * funded by the European Commission under Grant number 671517 through
 * the Horizon 2020 and 5G-PPP programmes. The authors would like to
 * acknowledge the contributions of their colleagues of the SONATA
 * partner consortium (www.sonata-nfv.eu).
 *
 * This work has been performed in the framework of the 5GTANGO project,
 * funded by the European Commission under Grant number 761493 through
 * the Horizon 2020 and 5G-PPP programmes. The authors would like to
 * acknowledge the contributions of their colleagues of the 5GTANGO
 * partner consortium (www.5gtango.eu).
 */

package tng.vnv.planner.controller

import tng.vnv.planner.utils.TangoLogger
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import tng.vnv.planner.WorkflowManager
import tng.vnv.planner.model.TestSet
import tng.vnv.planner.service.TestService

@RestController
@Api
@RequestMapping('/api/v1/test-sets')
class TestSetController {


    @Autowired
    WorkflowManager manager
    @Autowired
    TestService testService

    //Tango logger
    def tangoLogger = new TangoLogger()
    String tangoLoggerType = null;
    String tangoLoggerOperation = null;
    String tangoLoggerMessage = null;
    String tangoLoggerStatus = null;

    @GetMapping
    @ApiOperation(value="Find all test sets", notes="Finding all test sets")
    @ResponseBody
    List<TestSet> listAllTestSets() {
        tangoLoggerType = "I";
        tangoLoggerOperation = "TestSetController.listAllTestSets";
        tangoLoggerMessage = ("/api/v1/test-sets (find all test sets request received)");
        tangoLoggerStatus = "200";
        tangoLogger.log(tangoLoggerType, tangoLoggerOperation, tangoLoggerMessage, tangoLoggerStatus)

        testService.findAllTestSets()
    }

    @GetMapping('/{uuid}')
    @ApiOperation(value="Find a test set", notes="Finding test set by uuid")
    @ResponseBody
    TestSet findTestSet(@PathVariable String uuid) {
        tangoLoggerType = "I";
        tangoLoggerOperation = "TestSetController.findTestSet";
        tangoLoggerMessage = ("/api/v1/test-sets/{uuid} (find test set by uuid request received. UUID=${uuid})");
        tangoLoggerStatus = "200";
        tangoLogger.log(tangoLoggerType, tangoLoggerOperation, tangoLoggerMessage, tangoLoggerStatus)

        testService.findSetByUuid(uuid)
    }

    @DeleteMapping('{uuid}')
    @ApiOperation(value="Cancell a test set", notes="Cancelling test set by uuid")
    @ResponseBody
    void cancelTestSet(@PathVariable String uuid) {
        tangoLoggerType = "I";
        tangoLoggerOperation = "TestSetController.cancelTestSet";
        tangoLoggerMessage = ("/api/v1/test-sets/{uuid} (cancel test set by uuid request received. UUID=${uuid})");
        tangoLoggerStatus = "200";
        tangoLogger.log(tangoLoggerType, tangoLoggerOperation, tangoLoggerMessage, tangoLoggerStatus)

        manager.cancelTestSet(uuid)
    }
}
