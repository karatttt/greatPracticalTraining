-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('造价评估', '3', '1', 'productbudget', 'evaluate/productbudget/index', 1, 0, 'C', '0', '0', 'evaluate:productbudget:list', '#', 'admin', sysdate(), '', null, '造价评估菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('造价评估查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'evaluate:productbudget:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('造价评估新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'evaluate:productbudget:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('造价评估修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'evaluate:productbudget:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('造价评估删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'evaluate:productbudget:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('造价评估导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'evaluate:productbudget:export',       '#', 'admin', sysdate(), '', null, '');