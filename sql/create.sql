-- `project`
create table if not exists `project`
(
    `projectID` int null auto_increment comment '项目ID' primary key,
    `name` varchar(255) not null comment '名称',
    `require_content` text null comment '项目需求',
    `createTime` timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    `endTime` timestamp null comment '结束时间',
    `appraiserID` int null comment '评估员',
    `auditorID` int null comment '审核员',
    `tenantryID` int null comment '租户',
    `description` text null comment '描述'
) comment '`project`';
insert into `project` (`projectID`, `name`, `appraiserID`, `auditorID`, `tenantryID`, `description`) values (50, '软件造价分析平台', 6668112, 753560215, 94, '437');
insert into `project` (`projectID`, `name`, `appraiserID`, `auditorID`, `tenantryID`, `description`) values (62, '软件造价智慧系统', 56, 6340365, 508743, '9576509');
insert into `project` (`projectID`, `name`, `appraiserID`, `auditorID`, `tenantryID`, `description`) values (84, '软件平安造价', 8409922, 857289, 6223, '6945');
insert into `project` (`projectID`, `name`, `appraiserID`, `auditorID`, `tenantryID`, `description`) values (12, '软件造价通', 6423030, 7577, 785619607, '604');
insert into `project` (`projectID`, `name`, `appraiserID`, `auditorID`, `tenantryID`, `description`) values (84, '软件造价智慧系统', 392534857, 5977160000, 58454, '0');
insert into `project` (`projectID`, `name`, `appraiserID`, `auditorID`, `tenantryID`, `description`) values (6, '软件造价分析平台', 45867, 8, 14, '3678418175');
insert into `project` (`projectID`, `name`, `appraiserID`, `auditorID`, `tenantryID`, `description`) values (63, '软件造价智慧系统', 73, 7, 9, '276');
insert into `project` (`projectID`, `name`, `appraiserID`, `auditorID`, `tenantryID`, `description`) values (34, '软件造价分析平台', 3554061503, 751491, 3195079, '6826494');
insert into `project` (`projectID`, `name`, `appraiserID`, `auditorID`, `tenantryID`, `description`) values (26, '软件平安造价', 49328135, 176122, 2341, '90472');
insert into `project` (`projectID`, `name`, `appraiserID`, `auditorID`, `tenantryID`, `description`) values (36, '软件造价分析平台', 4982, 1208739481, 5888, '7199');
insert into `project` (`projectID`, `name`, `appraiserID`, `auditorID`, `tenantryID`, `description`) values (43, '软件平安造价', 4077307, 297828, 93210938, '79');
insert into `project` (`projectID`, `name`, `appraiserID`, `auditorID`, `tenantryID`, `description`) values (61, '软件造价平台', 8470457, 26514, 287853757, '3');
insert into `project` (`projectID`, `name`, `appraiserID`, `auditorID`, `tenantryID`, `description`) values (74, '软件造价通', 954972, 1176607, 736, '707958');
insert into `project` (`projectID`, `name`, `appraiserID`, `auditorID`, `tenantryID`, `description`) values (77, '软件造价通', 40591, 525369, 515, '15702');
insert into `project` (`projectID`, `name`, `appraiserID`, `auditorID`, `tenantryID`, `description`) values (65, '软件造价分析平台', 951143587, 924466, 4155303, '3');
insert into `project` (`projectID`, `name`, `appraiserID`, `auditorID`, `tenantryID`, `description`) values (89, '软件造价分析平台', 31164515, 443574797, 41, '13');
insert into `project` (`projectID`, `name`, `appraiserID`, `auditorID`, `tenantryID`, `description`) values (1, '软件造价分析平台', 52379, 1453670903, 8, '5792');
insert into `project` (`projectID`, `name`, `appraiserID`, `auditorID`, `tenantryID`, `description`) values (17, '软件平安造价', 81, 540, 61, '923171229');
insert into `project` (`projectID`, `name`, `appraiserID`, `auditorID`, `tenantryID`, `description`) values (55, '软件平安造价', 64084, 6, 9716792755, '5420');
insert into `project` (`projectID`, `name`, `appraiserID`, `auditorID`, `tenantryID`, `description`) values (87, '软件造价智慧系统', 5, 2189155, 8, '438574');
-- `productbudget`
create table if not exists `productbudget`
(
    `productID` bigint not null comment '项目ID' primary key,
    `PDR` float null comment 'PDR',
    `SF` float null comment 'SF',
    `BD` float null comment 'BD',
    `AT` float null comment 'AT',
    `QR` float null comment 'QR',
    `SL` float null comment 'SL',
    `DT` float null comment 'DT',
    `RSK` float null comment 'RSK',
    `F` float null comment 'F',
    `DNC` float null comment 'DNC',
    `SDC` float null comment 'SDC',
    `ESDC` float null comment 'ESDC'
) comment '`productbudget`';
insert into `productbudget` (`productID`, `PDR`, `SF`, `BD`, `AT`, `QR`, `SL`, `DT`, `RSK`, `F`, `DNC`, `SDC`, `ESDC`) values (1, 31668.145, 40849.336, 30928.283, 64710.133, 38716.297, 49567.12, 62231.516, 79914.76, 52953.117, 92306.91, 72090.875, 15053.803);
insert into `productbudget` (`productID`, `PDR`, `SF`, `BD`, `AT`, `QR`, `SL`, `DT`, `RSK`, `F`, `DNC`, `SDC`, `ESDC`) values (9097273157, 55076.105, 17740.799, 77747.67, 9291.44, 75440.07, 81614.016, 4642.3496, 87425.69, 86989.734, 77092.055, 35548.938, 91077.56);
insert into `productbudget` (`productID`, `PDR`, `SF`, `BD`, `AT`, `QR`, `SL`, `DT`, `RSK`, `F`, `DNC`, `SDC`, `ESDC`) values (291, 33305.914, 53303.445, 56357.527, 51340.746, 65192.055, 58887.266, 55788.344, 12074.244, 73116.23, 14445.704, 61372.797, 17349.137);
insert into `productbudget` (`productID`, `PDR`, `SF`, `BD`, `AT`, `QR`, `SL`, `DT`, `RSK`, `F`, `DNC`, `SDC`, `ESDC`) values (99938, 93466.48, 58361.645, 630.4205, 67297.94, 96555.18, 22910.887, 71813.41, 1594.7043, 87405.22, 44249.363, 54298.2, 97347.08);
insert into `productbudget` (`productID`, `PDR`, `SF`, `BD`, `AT`, `QR`, `SL`, `DT`, `RSK`, `F`, `DNC`, `SDC`, `ESDC`) values (5, 24290.775, 97706.305, 97264.24, 92695.76, 66076.23, 35279.227, 14994.037, 91347.14, 1891.1243, 19071.93, 32258.021, 11012.828);
insert into `productbudget` (`productID`, `PDR`, `SF`, `BD`, `AT`, `QR`, `SL`, `DT`, `RSK`, `F`, `DNC`, `SDC`, `ESDC`) values (718092268, 58288.355, 75887.336, 93197.125, 21821.285, 74338.22, 13060.594, 5307.3228, 33518.754, 9424.752, 57979.01, 68902.22, 61430.164);
insert into `productbudget` (`productID`, `PDR`, `SF`, `BD`, `AT`, `QR`, `SL`, `DT`, `RSK`, `F`, `DNC`, `SDC`, `ESDC`) values (71026666, 6729.752, 52880.477, 9317.637, 26139.21, 41038.293, 16927.814, 35501.777, 48017.89, 26871.33, 44440.02, 29216.408, 13401.377);
insert into `productbudget` (`productID`, `PDR`, `SF`, `BD`, `AT`, `QR`, `SL`, `DT`, `RSK`, `F`, `DNC`, `SDC`, `ESDC`) values (4306, 3152.6387, 35102.88, 89328.625, 79894.086, 74897.0, 10128.521, 73578.17, 84749.47, 82692.945, 92229.84, 59031.277, 31033.771);
insert into `productbudget` (`productID`, `PDR`, `SF`, `BD`, `AT`, `QR`, `SL`, `DT`, `RSK`, `F`, `DNC`, `SDC`, `ESDC`) values (861, 37163.34, 36322.375, 34792.668, 5057.0967, 92965.11, 15045.905, 58085.348, 48901.727, 56103.367, 70083.414, 82605.08, 53052.992);
insert into `productbudget` (`productID`, `PDR`, `SF`, `BD`, `AT`, `QR`, `SL`, `DT`, `RSK`, `F`, `DNC`, `SDC`, `ESDC`) values (815104, 82894.63, 86541.57, 68723.58, 47531.957, 35489.09, 22861.064, 93828.78, 22773.76, 1939.2968, 71356.04, 57175.273, 75432.016);
insert into `productbudget` (`productID`, `PDR`, `SF`, `BD`, `AT`, `QR`, `SL`, `DT`, `RSK`, `F`, `DNC`, `SDC`, `ESDC`) values (767635, 6171.3276, 74166.31, 65732.96, 11248.696, 8271.938, 43556.06, 82624.016, 65395.445, 16768.229, 92091.695, 29128.713, 70389.08);
insert into `productbudget` (`productID`, `PDR`, `SF`, `BD`, `AT`, `QR`, `SL`, `DT`, `RSK`, `F`, `DNC`, `SDC`, `ESDC`) values (8, 69198.28, 30718.236, 39723.65, 50050.312, 34775.223, 97120.44, 30549.8, 8762.384, 29352.617, 43598.824, 79158.02, 2386.3494);
insert into `productbudget` (`productID`, `PDR`, `SF`, `BD`, `AT`, `QR`, `SL`, `DT`, `RSK`, `F`, `DNC`, `SDC`, `ESDC`) values (661230160, 29206.533, 28587.717, 12633.104, 32405.95, 9540.242, 88751.49, 89440.445, 31844.514, 85164.2, 47689.645, 22174.871, 93555.12);
insert into `productbudget` (`productID`, `PDR`, `SF`, `BD`, `AT`, `QR`, `SL`, `DT`, `RSK`, `F`, `DNC`, `SDC`, `ESDC`) values (7, 98190.68, 66913.12, 40692.227, 21369.756, 23001.135, 56826.53, 98981.2, 27752.447, 24319.375, 53665.42, 80732.74, 30891.203);
insert into `productbudget` (`productID`, `PDR`, `SF`, `BD`, `AT`, `QR`, `SL`, `DT`, `RSK`, `F`, `DNC`, `SDC`, `ESDC`) values (392, 22334.82, 37144.06, 2851.051, 78901.47, 1416.7845, 24695.623, 63091.48, 74590.38, 25947.75, 18403.37, 32779.12, 73173.57);
insert into `productbudget` (`productID`, `PDR`, `SF`, `BD`, `AT`, `QR`, `SL`, `DT`, `RSK`, `F`, `DNC`, `SDC`, `ESDC`) values (8, 52492.9, 15532.13, 31630.152, 10425.657, 53085.62, 97333.625, 65116.51, 40976.41, 5386.5254, 9125.549, 2206.8977, 23945.451);
insert into `productbudget` (`productID`, `PDR`, `SF`, `BD`, `AT`, `QR`, `SL`, `DT`, `RSK`, `F`, `DNC`, `SDC`, `ESDC`) values (229774, 38744.996, 67576.11, 94839.34, 17353.53, 66341.47, 1652.6699, 37162.066, 95944.15, 43919.28, 91391.87, 83247.414, 20548.576);
insert into `productbudget` (`productID`, `PDR`, `SF`, `BD`, `AT`, `QR`, `SL`, `DT`, `RSK`, `F`, `DNC`, `SDC`, `ESDC`) values (42777898, 4411.3813, 51546.844, 52109.62, 7256.138, 14753.7705, 16172.373, 28356.254, 26159.674, 53682.83, 67124.09, 53868.39, 29560.209);
insert into `productbudget` (`productID`, `PDR`, `SF`, `BD`, `AT`, `QR`, `SL`, `DT`, `RSK`, `F`, `DNC`, `SDC`, `ESDC`) values (521, 28947.086, 84274.305, 14858.949, 31669.908, 59829.8, 97690.41, 83416.305, 78857.54, 7152.456, 47786.766, 745.3203, 79928.016);
insert into `productbudget` (`productID`, `PDR`, `SF`, `BD`, `AT`, `QR`, `SL`, `DT`, `RSK`, `F`, `DNC`, `SDC`, `ESDC`) values (521912, 48061.883, 84756.82, 54869.188, 60016.99, 40831.31, 56759.113, 75172.49, 35839.63, 6129.223, 57261.43, 72484.875, 44250.62);